package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.AnalyticDeviceDto;

import java.util.Map;
import java.util.UUID;

public interface AnalyticDeviceClient {

    Map<UUID, AnalyticDeviceDto> getAnalyticDevices();
}
