package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.grpc.GrpcAnalyticRouteClient;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.SessionService;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StateDeviceServiceImpl implements StateDeviceService {

    private final GrpcAnalyticRouteClient grpcAnalyticRouteClient;
    private final SessionService sessionService;

    @Override
    public List<StateDeviceDto> getFreeDevices() {
        List<SessionDto> inactiveSession = sessionService.getInactiveSession();
        if (inactiveSession != null && !inactiveSession.isEmpty()) {
            List<UUID> deviceIds = inactiveSession.stream()
                    .map(SessionDto::getDeviceId)
                    .collect(Collectors.toList());

            return grpcAnalyticRouteClient.getStateDevices(deviceIds);
        }

        return Collections.emptyList();
    }

    @Override
    public List<StateDeviceDto> getDevices() {
        return grpcAnalyticRouteClient.getStateDevices();
    }
}
