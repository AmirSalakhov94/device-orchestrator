package tech.itpark.deviceorchestrator.exception;

import org.springframework.http.HttpStatus;

public class NotFoundSessionDeviceException extends DeviceOrchestratorException {

    public NotFoundSessionDeviceException(String msg) {
        super(msg, HttpStatus.NOT_FOUND);
    }
}
