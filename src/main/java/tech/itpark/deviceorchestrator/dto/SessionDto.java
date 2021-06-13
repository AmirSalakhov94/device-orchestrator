package tech.itpark.deviceorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionDto {

    private UUID id;
    private UUID profileId;
    private UUID deviceId;
    private List<String> deviceImageUrls;
    private Instant start;
    private Instant end;
    private Integer usageTimeInSeconds;
    private BigDecimal cost;
}
