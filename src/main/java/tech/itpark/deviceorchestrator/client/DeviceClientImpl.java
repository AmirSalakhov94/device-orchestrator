package tech.itpark.deviceorchestrator.client;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.DeviceDto;

import java.util.List;

@Component
public class DeviceClientImpl implements DeviceClient {
    @Override
    public List<DeviceDto> getDevices() {
        return null;
    }
}
