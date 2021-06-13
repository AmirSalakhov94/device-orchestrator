package tech.itpark.deviceorchestrator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DeviceOrchestratorException extends RuntimeException {

    private final HttpStatus httpStatus;

    public DeviceOrchestratorException(HttpStatus httpStatus) {
        super();
        this.httpStatus = httpStatus;
    }

    public DeviceOrchestratorException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public DeviceOrchestratorException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
