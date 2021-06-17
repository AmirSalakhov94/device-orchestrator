package tech.itpark.deviceorchestrator.mapper;

import org.mapstruct.Mapper;
import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;
import tech.itpark.deviceorchestrator.model.AnalyticRoute;

@Mapper
public interface AnalyticRouteMapper {

    AnalyticRoute fromDto(AnalyticRouteDto analyticRoute);

    AnalyticRouteDto fromEntity(AnalyticRoute analyticRoute);
}
