package tech.itpark.deviceorchestrator.mapper;

import org.mapstruct.Mapper;
import tech.itpark.deviceorchestrator.dto.SessionDto;
import tech.itpark.deviceorchestrator.model.Session;

@Mapper
public interface SessionMapper {

    Session fromDto(SessionDto session);

    SessionDto fromEntity(Session session);
}
