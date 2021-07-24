package tech.itpark.deviceorchestrator.mapper;

import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.itpark.deviceanalyzer.grpc.StateDevice;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Mapper
public interface StateDeviceMapper {

    @Mapping(target = "deviceId", source = "deviceId", qualifiedByName = "stringToUuid")
    @Mapping(target = "recordTime", source = "recordTime", qualifiedByName = "timestampToInstant")
    StateDeviceDto fromGrpcResponse(StateDevice stateDevice);

    List<StateDeviceDto> fromGrpcResponse(List<StateDevice> stateDevices);

    @Named("stringToUuid")
    default UUID stringToUuid(String strUuid) {
        return UUID.fromString(strUuid);
    }

    @Named("timestampToInstant")
    default Instant timestampToInstant(Timestamp timestamp) {
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }
}
