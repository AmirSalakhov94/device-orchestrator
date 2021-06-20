package tech.itpark.deviceorchestrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "device-analyzer", contextId = "stateDeviceClient")
@RequestMapping("/state/device")
public interface StateDeviceClient {

    @GetMapping("/all")
    List<StateDeviceDto> getStateDevices();

    @GetMapping("/ids")
    List<StateDeviceDto> getStateDevicesForIds(@RequestBody List<UUID> deviceIds);
}
