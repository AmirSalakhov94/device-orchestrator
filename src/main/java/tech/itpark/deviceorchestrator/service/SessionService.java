package tech.itpark.deviceorchestrator.service;

import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;

import java.util.List;
import java.util.UUID;

public interface SessionService {

    List<SessionDto> getActiveSession();

    List<SessionDto> getSessionsByDeviceId(UUID deviceId);

    List<SessionDto> getSessionsByProfileId(UUID profileId);

    SessionDto getSessionById(UUID sessionId);

    void startSession(UUID profileId, StateSessionDto startSession);

    void finishSession(UUID profileId, StateSessionDto finishSession);
}
