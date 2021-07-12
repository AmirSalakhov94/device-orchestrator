package tech.itpark.deviceorchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;
import tech.itpark.deviceorchestrator.service.SessionService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orchestrator/session")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/all/device/{id}")
    public List<SessionDto> getSessionsByDeviceId(@PathVariable("id") UUID deviceId) {
        return sessionService.getSessionsByDeviceId(deviceId);
    }

    @GetMapping("/all/profile/{id}")
    public List<SessionDto> getSessionsByProfileId(@PathVariable("id") UUID profileId) {
        return sessionService.getSessionsByProfileId(profileId);
    }

    @GetMapping("/{sessionId}")
    public SessionDto getSession(@PathVariable("sessionId") UUID sessionId) {
        return sessionService.getSessionById(sessionId);
    }

    @PostMapping("/start")
    public void startSession(@RequestBody StateSessionDto sessionStart) {
        sessionService.startSession(sessionStart);
    }

    @PutMapping("/finish")
    public void finishSession(@RequestBody StateSessionDto sessionEnd) {
        sessionService.finishSession(sessionEnd);
    }
}
