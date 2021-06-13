package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.RouteDto;

public interface RouteClient {

    void createRoute(RouteDto route);

    void update(RouteDto route);
}
