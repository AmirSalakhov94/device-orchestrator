package tech.itpark.deviceorchestrator.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDto {

    private Double lat;
    private Double lng;
}
