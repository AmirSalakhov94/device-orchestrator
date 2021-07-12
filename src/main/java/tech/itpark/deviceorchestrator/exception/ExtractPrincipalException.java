package tech.itpark.deviceorchestrator.exception;

import org.springframework.http.HttpStatus;

public class ExtractPrincipalException extends DeviceOrchestratorException {

    public ExtractPrincipalException() {
        super(HttpStatus.FORBIDDEN);
    }

    public ExtractPrincipalException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
