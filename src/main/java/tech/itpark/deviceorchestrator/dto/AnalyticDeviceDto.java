package tech.itpark.deviceorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
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
