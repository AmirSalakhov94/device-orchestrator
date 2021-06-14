package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.DeviceClient;
import tech.itpark.deviceorchestrator.dto.DeviceDto;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.SessionService;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateDeviceServiceImpl implements StateDeviceService {

    private final DeviceClient deviceClient;
    private final SessionService sessionService;

    @Override
    public List<StateDeviceDto> getFreeDevices() {
        return null;
    }

    @Override
    public List<StateDeviceDto> getDevices() {
        List<DeviceDto> devices = deviceClient.getDevices();
        return null;
    }
}
