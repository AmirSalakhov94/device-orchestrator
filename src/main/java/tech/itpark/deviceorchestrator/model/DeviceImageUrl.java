package tech.itpark.deviceorchestrator.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceImageUrl {

    private String url;
    private Instant dateAdd;
}
