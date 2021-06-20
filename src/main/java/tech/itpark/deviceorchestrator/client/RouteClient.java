package tech.itpark.deviceorchestrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.itpark.deviceorchestrator.dto.RouteDto;

@FeignClient(name = "device-analyzer", contextId = "routeClient")
@RequestMapping("/route")
public interface RouteClient {

    @PostMapping("/start")
    void startRoute(@RequestBody RouteDto route);

    @PutMapping("/finish")
    void finishRoute(@RequestBody RouteDto route);
}
