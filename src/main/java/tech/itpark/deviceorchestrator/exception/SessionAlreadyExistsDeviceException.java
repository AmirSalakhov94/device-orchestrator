package tech.itpark.deviceorchestrator.exception;

import org.springframework.http.HttpStatus;

public class SessionAlreadyExistsDeviceException extends DeviceOrchestratorException {

    public SessionAlreadyExistsDeviceException(String msg) {
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
