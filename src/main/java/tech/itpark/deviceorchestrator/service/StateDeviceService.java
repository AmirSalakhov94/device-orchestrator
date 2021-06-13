package tech.itpark.deviceorchestrator.service;

import tech.itpark.deviceorchestrator.dto.StateDeviceDto;

import java.util.List;

public interface StateDeviceService {

    List<StateDeviceDto> getFreeDevices();

    List<StateDeviceDto> getDevices();
}
