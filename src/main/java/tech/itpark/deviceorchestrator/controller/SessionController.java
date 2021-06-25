package tech.itpark.deviceorchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.deviceorchestrator.dto.ProfileDto;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;
import tech.itpark.deviceorchestrator.service.SessionService;
import tech.itpark.deviceorchestrator.utils.ProfileUtil;

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
    public void startSession(@RequestHeader("X-Profile") String xProfile,
                             @RequestBody StateSessionDto sessionStart) {
        ProfileDto profile = ProfileUtil.getProfileWithHeader(xProfile);
        sessionService.startSession(profile.getId(), sessionStart);
    }

    @PutMapping("/finish")
    public void finishSession(@RequestHeader("X-Profile") String xProfile,
                              @RequestBody StateSessionDto sessionEnd) {
        ProfileDto profile = ProfileUtil.getProfileWithHeader(xProfile);
        sessionService.finishSession(profile.getId(), sessionEnd);
    }
}
