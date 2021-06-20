package tech.itpark.deviceorchestrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.itpark.deviceorchestrator.dto.DeviceDto;

import java.util.List;

@FeignClient(name = "device-profile", contextId = "deviceClient")
@RequestMapping("/device")
public interface DeviceClient {

    @GetMapping("/all")
    List<DeviceDto> getDevices();
}
