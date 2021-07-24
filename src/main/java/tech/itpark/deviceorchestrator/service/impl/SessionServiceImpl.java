package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.grpc.GrpcAnalyticRouteClient;
import tech.itpark.deviceorchestrator.dto.*;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;
import tech.itpark.deviceorchestrator.exception.NotFoundActiveSessionDeviceException;
import tech.itpark.deviceorchestrator.exception.NotFoundSessionDeviceException;
import tech.itpark.deviceorchestrator.exception.SessionAlreadyExistsDeviceException;
import tech.itpark.deviceorchestrator.mapper.AnalyticRouteMapper;
import tech.itpark.deviceorchestrator.mapper.SessionMapper;
import tech.itpark.deviceorchestrator.model.Session;
import tech.itpark.deviceorchestrator.repository.SessionRepository;
import tech.itpark.deviceorchestrator.security.principal.PrincipalExtractor;
import tech.itpark.deviceorchestrator.service.CostCalculator;
import tech.itpark.deviceorchestrator.service.SessionService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;
    private final SessionRepository sessionRepository;
    private final AnalyticRouteMapper analyticRouteMapper;
    private final CostCalculator costCalculator;
    private final PrincipalExtractor<ProfileDto> principalExtractor;
    private final GrpcAnalyticRouteClient grpcAnalyticRouteClient;

    @Override
    public List<SessionDto> getInactiveSession() {
        return sessionRepository.findByIsActiveIsFalse()
                .stream()
                .map(sessionMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionDto> getActiveSession() {
        return sessionRepository.findByIsActiveIsTrue()
                .stream()
                .map(sessionMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionDto> getSessionsByDeviceId(UUID deviceId) {
        return sessionRepository.findByDeviceId(deviceId)
                .stream()
                .map(sessionMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionDto> getSessionsByProfileId(UUID profileId) {
        return sessionRepository.findByProfileId(profileId)
                .stream()
                .map(sessionMapper::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId)
                .map(sessionMapper::fromEntity)
                .orElseThrow(() -> new NotFoundSessionDeviceException(String.format("Session not found by id : %s", sessionId)));
    }

    @Override
    public void startSession(StateSessionDto startSession) {
        ProfileDto profile = principalExtractor.getPrincipal();
        UUID deviceId = startSession.getDeviceId();
        sessionRepository.findByDeviceIdAndProfileIdAndIsActiveTrue(deviceId, profile.getId())
                .ifPresent(session -> {
                    throw new SessionAlreadyExistsDeviceException(
                            String.format("Session for profile: %s and device: %s already exists",
                                    profile.getId(), startSession.getDeviceId()));
                });

        Session session = sessionRepository.save(sessionRepository.save(Session.builder()
                .profileId(profile.getId())
                .deviceId(startSession.getDeviceId())
                .typeDevice(startSession.getTypeDevice())
                .start(Instant.now())
                .startDevicePictureUrls(startSession.getDevicePictureUrls())
                .isActive(true)
                .build()));

        RouteDto route = RouteDto.builder()
                .sessionId(session.getId())
                .isActive(true)
                .deviceId(deviceId)
                .start(startSession.getTime())
                .build();

        grpcAnalyticRouteClient.startRoute(route);
    }

    @Override
    public void finishSession(StateSessionDto finishSession) {
        ProfileDto profile = principalExtractor.getPrincipal();
        UUID deviceId = finishSession.getDeviceId();

        Session session = sessionRepository.findByDeviceIdAndProfileIdAndIsActiveTrue(deviceId, profile.getId())
                .orElseThrow(() -> new NotFoundActiveSessionDeviceException(
                        String.format("Not found active session for profile %s and device %s", profile.getId(), deviceId)));

        grpcAnalyticRouteClient.finishRoute(RouteDto.builder()
                .sessionId(session.getId())
                .start(session.getStart())
                .end(Instant.now())
                .isActive(false)
                .deviceId(deviceId)
                .build());


        AnalyticRouteDto analyticRoute = grpcAnalyticRouteClient.getAnalyticByDeviceAndSession(deviceId, session.getId());
        session.setAnalyticRoute(analyticRouteMapper.fromDto(analyticRoute));

        TypeDevice typeDevice = session.getTypeDevice();
        BigDecimal cost = costCalculator.cost(typeDevice, analyticRoute.getDistance());
        session.setCost(cost);

        session.setEndDevicePictureUrls(finishSession.getDevicePictureUrls());
        session.setEnd(Instant.now());
        session.setIsActive(false);
        sessionRepository.save(session);
    }
}
