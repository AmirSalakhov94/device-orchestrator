package tech.itpark.deviceorchestrator.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {

    private UUID id;
    private UUID profileId;
    private UUID deviceId;
    private Instant start;
    private Instant end;
    private long usageTimeInSeconds;
    private BigDecimal cost;
    private Boolean isActive;
    private List<String> startDevicePictureUrls;
    private List<String> endDevicePictureUrls;
}
