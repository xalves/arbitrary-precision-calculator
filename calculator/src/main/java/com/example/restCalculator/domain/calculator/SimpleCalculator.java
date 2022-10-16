package com.example.restCalculator.domain.calculator;

import com.example.restCalculator.domain.config.CalculatorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class SimpleCalculator implements Calculator {

    private final CalculatorProperties calculatorProperties;

    @Autowired
    public SimpleCalculator(CalculatorProperties calculatorProperties) {
        this.calculatorProperties = calculatorProperties;
    }

    @Override
    public BigDecimal sum(BigDecimal a, BigDecimal b) {
        return a.add(b, getMathContext());
    }

    @Override
    public BigDecimal subtraction(BigDecimal a, BigDecimal b) {
        return a.subtract(b, getMathContext());
    }

    @Override
    public BigDecimal division(BigDecimal a, BigDecimal b) {
        return a.divide(b, getMathContext());
    }

    @Override
    public BigDecimal multiplication(BigDecimal a, BigDecimal b) {
        return a.multiply(b, getMathContext());
    }

    private MathContext getMathContext() {
        return new MathContext(calculatorProperties.getConfiguredPrecision(), RoundingMode.UP);
    }
}
