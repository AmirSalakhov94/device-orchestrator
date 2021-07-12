package tech.itpark.deviceorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@EnableDiscoveryClient
@EnableFeignClients
@EnableMongoRepositories
@SpringBootApplication
public class DeviceOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceOrchestratorApplication.class, args);
    }
}
