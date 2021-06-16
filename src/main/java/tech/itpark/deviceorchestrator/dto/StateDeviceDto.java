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
    private boolean isUsed;
    private double lat;
    private double lng;
    private float chargingPercentage;
    private TypeDevice type;
    private Instant registrationDate;
}
