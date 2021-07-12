package tech.itpark.deviceorchestrator.service.impl;

import org.springframework.stereotype.Component;
import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;
import tech.itpark.deviceorchestrator.service.CostCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CostCalculatorImpl implements CostCalculator {

    @Override
    public BigDecimal cost(TypeDevice type, double distance) {
        BigDecimal min = new BigDecimal(135.11);
        BigDecimal max = new BigDecimal(1234.14);
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
