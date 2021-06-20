package tech.itpark.deviceorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@EnableFeignClients
@EnableMongoRepositories
@SpringBootApplication
public class DeviceOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceOrchestratorApplication.class, args);
    }

}
