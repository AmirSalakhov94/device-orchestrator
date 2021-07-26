package tech.itpark.deviceorchestrator.client.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.ServiceInstanceChooser;
import org.springframework.stereotype.Component;
import tech.itpark.deviceanalyzer.grpc.*;
import tech.itpark.deviceorchestrator.dto.AnalyticRouteDto;
import tech.itpark.deviceorchestrator.dto.RouteDto;
import tech.itpark.deviceorchestrator.dto.StateDeviceDto;
import tech.itpark.deviceorchestrator.mapper.AnalyticRouteMapper;
import tech.itpark.deviceorchestrator.mapper.StateDeviceMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GrpcAnalyticRouteClient {

    private static final String SERVICE_ID = "grpc-device-analyzer";

    private final ServiceInstanceChooser serviceInstanceChooser;
    private final AnalyticRouteMapper analyticRouteMapper;
    private final StateDeviceMapper stateDeviceMapper;

    public AnalyticRouteDto getAnalyticByDeviceAndSession(UUID deviceId, String sessionId) {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        AnalyticRouteRequest analyticRequest = AnalyticRouteRequest.newBuilder()
                .setDeviceId(deviceId.toString())
                .setSessionId(sessionId)
                .build();

        AnalyticRouteResponse analyticRouteResponse = analyticRouteService.getAnalyticByDeviceAndSession(analyticRequest);
        AnalyticRouteDto analyticRoute = analyticRouteMapper.fromGrpcResponse(analyticRouteResponse);
        managedChannel.shutdown();

        return analyticRoute;
    }

    public void startRoute(RouteDto route) {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        Instant start = route.getStart();
        Timestamp startTimestamp = Timestamp.newBuilder()
                .setSeconds(start.getEpochSecond())
                .setNanos(start.getNano())
                .build();

        RouteRequest routeRequest = RouteRequest.newBuilder()
                .setDeviceId(route.getDeviceId().toString())
                .setSessionId(route.getSessionId())
                .setIsActive(route.getIsActive())
                .setStart(startTimestamp)
                .build();

        analyticRouteService.startRoute(routeRequest);
        managedChannel.shutdown();
    }

    public void finishRoute(RouteDto route) {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        Instant start = route.getStart();
        Timestamp startTimestamp = Timestamp.newBuilder()
                .setSeconds(start.getEpochSecond())
                .setNanos(start.getNano())
                .build();

        Instant end = route.getEnd();
        Timestamp endTimestamp = Timestamp.newBuilder()
                .setSeconds(end.getEpochSecond())
                .setNanos(end.getNano())
                .build();

        RouteRequest routeRequest = RouteRequest.newBuilder()
                .setDeviceId(route.getDeviceId().toString())
                .setSessionId(route.getSessionId())
                .setIsActive(route.getIsActive())
                .setStart(startTimestamp)
                .setEnd(endTimestamp)
                .build();

        analyticRouteService.finishRoute(routeRequest);
        managedChannel.shutdown();
    }

    public List<StateDeviceDto> getStateFreeDevices() {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        StateDeviceResponse stateDeviceResponse = analyticRouteService.getStateFreeDevices(Empty.getDefaultInstance());
        List<StateDevice> stateDevicesList = stateDeviceResponse.getStateDevicesList();

        managedChannel.shutdown();

        return stateDeviceMapper.fromGrpcResponse(stateDevicesList);
    }

    public List<StateDeviceDto> getStateDevices() {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        StateDeviceResponse stateDeviceResponse = analyticRouteService.getStateDevices(Empty.getDefaultInstance());
        List<StateDevice> stateDevicesList = stateDeviceResponse.getStateDevicesList();

        managedChannel.shutdown();

        return stateDeviceMapper.fromGrpcResponse(stateDevicesList);
    }

    public List<StateDeviceDto> getStateDevices(List<UUID> deviceIds) {
        ServiceInstance choose = serviceInstanceChooser.choose(SERVICE_ID);
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(choose.getHost(), choose.getPort())
                .usePlaintext()
                .build();

        AnalyticRouteServiceGrpc.AnalyticRouteServiceBlockingStub analyticRouteService
                = AnalyticRouteServiceGrpc.newBlockingStub(managedChannel);

        List<DeviceId> ids = deviceIds.stream()
                .map(deviceId -> DeviceId.newBuilder()
                        .setDeviceId(deviceId.toString())
                        .build())
                .collect(Collectors.toList());

        DeviceIdsRequest deviceIdsRequest = DeviceIdsRequest.newBuilder()
                .addAllDeviceIds(ids)
                .build();
        StateDeviceResponse stateDeviceResponse = analyticRouteService.getStateDevicesForIds(deviceIdsRequest);
        List<StateDevice> stateDevicesList = stateDeviceResponse.getStateDevicesList();
        managedChannel.shutdown();

        return stateDeviceMapper.fromGrpcResponse(stateDevicesList);
    }
}
