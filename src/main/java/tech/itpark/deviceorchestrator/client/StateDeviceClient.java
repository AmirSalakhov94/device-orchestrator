package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.StateDeviceDto;

import java.util.List;
import java.util.UUID;

public interface StateDeviceClient {

    List<StateDeviceDto> getStateDevices();

    List<StateDeviceDto> getStateDevicesForIds(List<UUID> deviceIds);
}
