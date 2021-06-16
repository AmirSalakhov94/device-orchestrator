package tech.itpark.deviceorchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/state/device")
public class StateDeviceController {

    private final StateDeviceService stateDeviceService;

    @GetMapping("/all")
    public List<StateDeviceDto> getDevices() {
        return stateDeviceService.getDevices();
    }

    @GetMapping("/free")
    public List<StateDeviceDto> getFreeDevices() {
        return stateDeviceService.getFreeDevices();
    }
}
