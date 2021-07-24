package tech.itpark.deviceorchestrator.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticRouteDto {

    private UUID deviceId;
    private String sessionId;
    private UUID routeId;
    private Double distance;
    private List<CoordinateDto> route;
    private Float maxSpeedInMeters;
    private Float avgSpeedInMeters;
    private Integer usagePeriodInSeconds;
}
