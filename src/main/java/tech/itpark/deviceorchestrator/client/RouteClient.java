package tech.itpark.deviceorchestrator.client;

import tech.itpark.deviceorchestrator.dto.RouteDto;

public interface RouteClient {

    void startRoute(RouteDto route);

    void finishRoute(RouteDto route);
}
