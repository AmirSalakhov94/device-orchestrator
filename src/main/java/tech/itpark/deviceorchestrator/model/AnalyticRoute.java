package tech.itpark.deviceorchestrator.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.itpark.deviceorchestrator.dto.CoordinateDto;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "analyticRoute")
public class AnalyticRoute {

    private UUID deviceId;
    private String sessionId;
    private UUID routeId;
    private double distance;
    private List<CoordinateDto> route;
    private float maxSpeedInMeters;
    private float avgSpeedInMeters;
    private int usagePeriodInSeconds;
}
