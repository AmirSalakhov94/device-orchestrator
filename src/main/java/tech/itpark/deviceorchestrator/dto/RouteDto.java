package tech.itpark.deviceorchestrator.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RouteDto {

    private UUID sessionId;
    private UUID deviceId;
    private Boolean isActive;
    private Instant start;
    private Instant end;
}
