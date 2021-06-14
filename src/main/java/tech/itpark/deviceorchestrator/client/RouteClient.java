package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.RouteDto;

import java.util.UUID;

public interface RouteClient {

    UUID createRoute(RouteDto route);

    void update(RouteDto route);
}
