package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateDeviceServiceImpl implements StateDeviceService {

    @Override
    public List<StateDeviceDto> getFreeDevices() {
        return null;
    }

    @Override
    public List<StateDeviceDto> getDevices() {
        return null;
    }
}
