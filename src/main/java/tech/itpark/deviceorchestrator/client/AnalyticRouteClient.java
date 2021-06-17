package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;

import java.util.UUID;

public interface AnalyticRouteClient {

    AnalyticRouteDto getAnalyticRouteByDeviceIdAndRouteId(UUID deviceId, UUID sessionId);
}
