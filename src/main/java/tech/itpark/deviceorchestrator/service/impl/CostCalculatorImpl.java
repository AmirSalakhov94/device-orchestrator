package tech.itpark.deviceorchestrator.service.impl;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;
import tech.itpark.deviceorchestrator.service.CostCalculator;

import java.math.BigDecimal;

@Component
public class CostCalculatorImpl implements CostCalculator {

    @Override
    public BigDecimal cost(TypeDevice type, double distance) {
        return null;
    }
}
