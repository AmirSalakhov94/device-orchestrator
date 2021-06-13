package tech.itpark.deviceorchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StateSessionDto {

    private UUID profileId;
    private UUID deviceId;
    private List<String> devicePictureUrls;
    private Instant time;
}
