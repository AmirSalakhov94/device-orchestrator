package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.RouteClient;
import tech.itpark.deviceorchestrator.dto.RouteDto;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;
import tech.itpark.deviceorchestrator.exception.NotFoundActiveSessionDeviceException;
import tech.itpark.deviceorchestrator.exception.NotFoundSessionDeviceException;
import tech.itpark.deviceorchestrator.mapper.SessionMapper;
import tech.itpark.deviceorchestrator.model.Session;
import tech.itpark.deviceorchestrator.repository.SessionRepository;
import tech.itpark.deviceorchestrator.service.SessionService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionMapper sessionMapper;
    private final SessionRepository sessionRepository;
    private final RouteClient routeClient;

    @Override
    public List<SessionDto> getActiveSession() {
        return sessionRepository.findByActiveIsTrue()
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
    public SessionDto getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId)
                .map(sessionMapper::fromEntity)
                .orElseThrow(() -> new NotFoundSessionDeviceException(String.format("Session not found by id : %s", sessionId)));
    }

    @Override
    public void startSession(StateSessionDto startSession) {
        UUID deviceId = startSession.getDeviceId();
        UUID sessionId = sessionRepository.save(sessionRepository.save(Session.builder()
                .profileId(startSession.getProfileId())
                .deviceId(startSession.getDeviceId())
                .start(startSession.getTime())
                .startDevicePictureUrls(startSession.getDevicePictureUrls())
                .isActive(true)
                .build())).getId();

        RouteDto route = RouteDto.builder()
                .sessionId(sessionId)
                .deviceId(deviceId)
                .start(startSession.getTime())
                .build();

        routeClient.startRoute(route);
    }

    @Override
    public void finishSession(StateSessionDto finishSession) {
        UUID deviceId = finishSession.getDeviceId();
        UUID profileId = finishSession.getProfileId();

        Session session = sessionRepository.findByDeviceIdAndProfileIdAndActiveTrue(deviceId, profileId)
                .orElseThrow(() -> new NotFoundActiveSessionDeviceException(
                        String.format("Not found active session for profile %s and device %s", profileId, deviceId)));

        session.setEndDevicePictureUrls(finishSession.getDevicePictureUrls());
        session.setEnd(finishSession.getTime());
        session.setActive(false);
        //  todo: calculate cost, distance etc
        routeClient.finishRoute(RouteDto.builder()
                .sessionId(session.getId())
                .start(session.getStart())
                .end(finishSession.getTime())
                .deviceId(deviceId)
                .build());

        sessionRepository.save(session);
    }
}
