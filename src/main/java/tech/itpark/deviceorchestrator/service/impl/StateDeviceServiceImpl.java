package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.AnalyticDeviceClient;
import tech.itpark.deviceorchestrator.client.DeviceClient;
import tech.itpark.deviceorchestrator.dto.AnalyticDeviceDto;
import tech.itpark.deviceorchestrator.dto.DeviceDto;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.SessionService;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StateDeviceServiceImpl implements StateDeviceService {

    private final DeviceClient deviceClient;
    private final AnalyticDeviceClient analyticDeviceClient;
    private final SessionService sessionService;

    @Override
    public List<StateDeviceDto> getFreeDevices() {
        List<DeviceDto> devices = deviceClient.getDevices();
        List<SessionDto> activeSession = sessionService.getActiveSession();
        Map<UUID, AnalyticDeviceDto> analyticDevices = analyticDeviceClient.getAnalyticDevices();
        return devices.stream()
                .map(device -> {
                    UUID id = device.getId();
                    AnalyticDeviceDto analyticDevice = analyticDevices.get(id);
                    if (analyticDevice != null && !analyticDevice.isUsed()) {
                        return StateDeviceDto.builder()
                                .deviceId(id)
                                .serialNumber(device.getSerialNumber())
                                .type(device.getType())
                                .chargingPercentage(analyticDevice.getCurrentChargingPercentage())
                                .lat(analyticDevice.getLat())
                                .lng(analyticDevice.getLng())
                                .isUsed(analyticDevice.isUsed())
                                .build();
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDeviceDto> getDevices() {
        List<DeviceDto> devices = deviceClient.getDevices();
        Map<UUID, AnalyticDeviceDto> analyticDevices = analyticDeviceClient.getAnalyticDevices();
        return devices.stream()
                .map(device -> {
                    UUID id = device.getId();
                    AnalyticDeviceDto analyticDeviceDto = analyticDevices.get(id);
                    if (analyticDeviceDto != null) {
                        return StateDeviceDto.builder()
                                .deviceId(id)
                                .serialNumber(device.getSerialNumber())
                                .type(device.getType())
                                .chargingPercentage(analyticDeviceDto.getCurrentChargingPercentage())
                                .lat(analyticDeviceDto.getLat())
                                .lng(analyticDeviceDto.getLng())
                                .isUsed(analyticDeviceDto.isUsed())
                                .build();
                    }

                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
