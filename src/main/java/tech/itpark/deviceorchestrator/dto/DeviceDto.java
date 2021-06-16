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
public class DeviceDto {

    private UUID id;
    private String serialNumber;
    private String password;
    private TypeDevice type;
    private Instant registrationDate;
}
