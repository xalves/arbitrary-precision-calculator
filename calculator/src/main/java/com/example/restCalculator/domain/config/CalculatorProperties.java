package com.example.restCalculator.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "calculator")
@ConfigurationPropertiesScan
@ConstructorBinding
public class CalculatorProperties {

    private final int precision;

    public CalculatorProperties(int precision) {
        this.precision = precision;
    }

    public int getConfiguredPrecision() {
        return precision;
    }
}
