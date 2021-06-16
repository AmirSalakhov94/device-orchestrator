package tech.itpark.deviceorchestrator.service;

import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.dto.StateSessionDto;

import java.util.List;
import java.util.UUID;

public interface SessionService {

    List<SessionDto> getActiveSession();

    List<SessionDto> getSessionsByDeviceId(UUID deviceId);

    SessionDto getSessionById(UUID sessionId);

    void startSession(StateSessionDto startSession);

    void finishSession(StateSessionDto finishSession);
}
