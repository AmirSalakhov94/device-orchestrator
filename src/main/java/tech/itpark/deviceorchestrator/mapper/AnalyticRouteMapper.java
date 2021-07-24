package tech.itpark.deviceorchestrator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.itpark.deviceanalyzer.grpc.AnalyticRouteResponse;
import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;
import tech.itpark.deviceorchestrator.model.AnalyticRoute;

import java.util.UUID;

@Mapper
public interface AnalyticRouteMapper {

    AnalyticRoute fromDto(AnalyticRouteDto analyticRoute);

    AnalyticRouteDto fromEntity(AnalyticRoute analyticRoute);

    @Mapping(target = "deviceId", source = "deviceId", qualifiedByName = "stringToUuid")
    @Mapping(target = "routeId", source = "routeId", qualifiedByName = "stringToUuid")
    @Mapping(target = "route", source = "routeList")
    AnalyticRouteDto fromGrpcResponse(AnalyticRouteResponse analyticRoute);

    @Named("stringToUuid")
    default UUID stringToUuid(String strUuid) {
        return UUID.fromString(strUuid);
    }
}
