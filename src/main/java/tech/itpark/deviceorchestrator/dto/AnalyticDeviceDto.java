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
    private Long totalMileage;
    private Float currentChargingPercentage;
    private Integer numberOfUses;
    private Boolean isActive;
    private Boolean isUsed;
    private UUID lastRouteId;
    private Double lat;
    private Double lng;
}
