package tech.itpark.deviceorchestrator.client;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;

import java.util.List;
import java.util.UUID;

@Component
public class StateDeviceClientImpl implements StateDeviceClient {

    @Override
    public List<StateDeviceDto> getStateDevices() {
        return null;
    }

    @Override
    public List<StateDeviceDto> getStateDevicesForIds(List<UUID> deviceIds) {
        return null;
    }
}
