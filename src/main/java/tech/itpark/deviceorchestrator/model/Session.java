package tech.itpark.deviceorchestrator.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "session")
public class Session {

    @Id
    private String id;
    private UUID profileId;
    private UUID deviceId;
    private TypeDevice typeDevice;
    private Instant start;
    private Instant end;
    private BigDecimal cost;
    private Boolean isActive;
    private List<String> startDevicePictureUrls;
    private List<String> endDevicePictureUrls;
    private AnalyticRoute analyticRoute;
}
