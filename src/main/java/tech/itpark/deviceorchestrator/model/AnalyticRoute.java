package tech.itpark.deviceorchestrator.model;

import lombok.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticRoute {

    private UUID deviceId;
    private UUID sessionId;
    private UUID routeId;
    private double distance;
    private List<Pair<Double, Double>> routes;
    private float maxSpeedInMeters;
    private float avgSpeedInMeters;
    private int usagePeriodInSeconds;
}
