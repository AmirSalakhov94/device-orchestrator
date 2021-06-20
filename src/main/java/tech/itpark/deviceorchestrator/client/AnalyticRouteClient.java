package tech.itpark.deviceorchestrator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;

import java.util.UUID;

@FeignClient(name = "device-analyzer", contextId = "analyticRouteClient")
@RequestMapping("/analytic/route")
public interface AnalyticRouteClient {

    @GetMapping("/{deviceId}/{sessionId}")
    AnalyticRouteDto getAnalyticRouteByDeviceIdAndRouteId(@PathVariable("deviceId") UUID deviceId,
                                                          @PathVariable("sessionId") UUID sessionId);
}
