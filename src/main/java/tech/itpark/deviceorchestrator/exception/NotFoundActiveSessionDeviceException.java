package tech.itpark.deviceorchestrator.exception;

import org.springframework.http.HttpStatus;

public class NotFoundActiveSessionDeviceException extends DeviceOrchestratorException {

    public NotFoundActiveSessionDeviceException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
