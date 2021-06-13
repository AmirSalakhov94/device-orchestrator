package tech.itpark.deviceorchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.service.SessionService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/{deviceId}/sessions")
    public List<SessionDto> getSessions(@PathVariable("deviceId") UUID deviceId) {
        return sessionService.getSessionsByDeviceId(deviceId);
    }

    @GetMapping("/{sessionId}")
    public SessionDto getSession(@PathVariable("sessionId") UUID sessionId) {
        return sessionService.getSessionById(sessionId);
    }

    @PostMapping("session/start")
    public void startSession(StateSessionDto sessionStart) {
        sessionService.startSession(sessionStart);
    }

    @PutMapping("session/finish")
    public void finishSession(StateSessionDto sessionEnd) {
        sessionService.finishSession(sessionEnd);
    }
}
