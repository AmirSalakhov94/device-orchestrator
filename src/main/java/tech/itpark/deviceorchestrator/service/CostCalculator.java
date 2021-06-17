package tech.itpark.deviceorchestrator.service;

import tech.itpark.deviceorchestrator.dto.enums.TypeDevice;

import java.math.BigDecimal;

public interface CostCalculator {

    BigDecimal cost(TypeDevice type, double distance);
}
