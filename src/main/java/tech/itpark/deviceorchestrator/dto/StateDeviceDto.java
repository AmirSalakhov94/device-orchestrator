package tech.itpark.deviceorchestrator.dto;

import lombok.*;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDeviceDto {

    private UUID deviceId;
    private Double lat;
    private Double lng;
    private Float chargingPercentage;

    @Builder.Default
    private TypeDevice type = TypeDevice.SCOOTER;
    private Instant recordTime;
}
