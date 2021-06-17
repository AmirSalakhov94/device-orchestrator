package tech.itpark.deviceorchestrator.dto;

import lombok.*;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateSessionDto {

    private UUID profileId;
    private UUID deviceId;
    private TypeDevice typeDevice;
    private List<String> devicePictureUrls;
    private Instant time;
}
