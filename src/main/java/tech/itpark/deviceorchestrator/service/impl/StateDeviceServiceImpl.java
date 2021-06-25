package tech.itpark.deviceorchestrator.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.itpark.deviceorchestrator.client.DeviceClient;
import tech.itpark.deviceorchestrator.client.StateDeviceClient;
import tech.itpark.deviceorchestrator.dto.DeviceDto;
import tech.itpark.deviceorchestrator.dto.ListWrapper;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.service.SessionService;
import tech.itpark.deviceorchestrator.service.StateDeviceService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StateDeviceServiceImpl implements StateDeviceService {

    private final StateDeviceClient stateDeviceClient;
    private final DeviceClient deviceClient;
    private final SessionService sessionService;

    //todo change
    @Override
    public List<StateDeviceDto> getFreeDevices() {
        List<DeviceDto> devices = deviceClient.getDevices();
        List<SessionDto> activeSession = sessionService.getActiveSession();
        if (activeSession != null && !activeSession.isEmpty()) {
            List<UUID> deviceIds = activeSession.stream()
                    .map(SessionDto::getDeviceId)
                    .filter(sessionDeviceId -> devices.stream().noneMatch(deviceDto -> deviceDto.getId().equals(sessionDeviceId)))
                    .collect(Collectors.toList());
            return stateDeviceClient.getStateDevicesForIds(new ListWrapper<>(deviceIds));
        }

        return stateDeviceClient.getStateDevicesForIds(new ListWrapper<>(devices.stream().map(DeviceDto::getId).collect(Collectors.toList())));
    }

    @Override
    public List<StateDeviceDto> getDevices() {
        return stateDeviceClient.getStateDevices();
    }
}
