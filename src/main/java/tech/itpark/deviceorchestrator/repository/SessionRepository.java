package tech.itpark.deviceorchestrator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.itpark.deviceorchestrator.model.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends MongoRepository<Session, UUID> {

    List<Session> findByIsActiveIsFalse();

    List<Session> findByIsActiveIsTrue();

    List<Session> findByDeviceId(UUID deviceId);

    List<Session> findByProfileId(UUID profileId);

    Optional<Session> findByDeviceIdAndProfileIdAndIsActiveTrue(UUID deviceId, UUID profileId);
}
