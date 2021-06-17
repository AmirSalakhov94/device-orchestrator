package tech.itpark.deviceanalyzer.service;

import tech.itpark.deviceanalyzer.dto.enums.TypeDevice;

import java.math.BigDecimal;

public interface CostCalculator {

    BigDecimal cost(TypeDevice type, double distance);
}
