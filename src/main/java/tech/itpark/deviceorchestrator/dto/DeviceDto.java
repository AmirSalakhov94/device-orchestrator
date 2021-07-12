package tech.itpark.deviceorchestrator.dto;

import lombok.*;
import tech.itpark.deviceorchestrator.dto.enums.Role;
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
    private String name;
    private String password;
    private String serialNumber;
    private TypeDevice type;
    private Role role;
    private Instant createDate;
}
