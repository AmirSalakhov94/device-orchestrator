package tech.itpark.deviceorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateDeviceDto {

    private UUID id;
    private String serialNumber;
    private double lat;
    private double lng;
    private float chargingPercentage;
    private TypeDevice type;
    private Instant registrationDate;
}
