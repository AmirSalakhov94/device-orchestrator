package tech.itpark.deviceorchestrator.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticDeviceDto {

    private UUID deviceId;
    private long totalMileage;
    private float currentChargingPercentage;
    private int numberOfUses;
    private boolean isActive;
    private boolean isUsed;
    private UUID lastRouteId;
    private double lat;
    private double lng;
}
