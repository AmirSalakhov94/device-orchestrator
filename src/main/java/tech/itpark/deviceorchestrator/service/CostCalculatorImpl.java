package tech.itpark.deviceorchestrator.service;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.math.BigDecimal;

@Component
public class CostCalculatorImpl implements CostCalculator {

    @Override
    public BigDecimal cost(TypeDevice type, double distance) {
        return null;
    }
}
