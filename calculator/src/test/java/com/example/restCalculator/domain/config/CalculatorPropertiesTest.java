package com.example.restCalculator.domain.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = CalculatorProperties.class)
@TestPropertySource("classpath:test-application.properties")
class CalculatorPropertiesTest {

    @Autowired
    private CalculatorProperties calculatorProperties;

    @Test
    public void shouldCorrectlyReadProperties() {
        assertEquals(20, calculatorProperties.getConfiguredPrecision());
    }

}