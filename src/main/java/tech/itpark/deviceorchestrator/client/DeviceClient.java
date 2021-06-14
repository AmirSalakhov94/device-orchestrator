package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.DeviceDto;

import java.util.List;

public interface DeviceClient {

    List<DeviceDto> getDevices();
}
