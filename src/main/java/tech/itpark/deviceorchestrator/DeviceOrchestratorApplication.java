package tech.itpark.deviceorchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class DeviceOrchestratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceOrchestratorApplication.class, args);
    }

}
