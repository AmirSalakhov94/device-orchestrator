package tech.itpark.deviceorchestrator.client;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;

import java.util.UUID;

@Component
public class AnalyticRouteClientImpl implements AnalyticRouteClient {
    @Override
    public AnalyticRouteDto getAnalyticRouteByDeviceIdAndRouteId(UUID deviceId, UUID sessionId) {
        return null;
    }
}
