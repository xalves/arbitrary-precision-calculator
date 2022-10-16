package com.example.restCalculator.domain;


import com.example.restCalculator.domain.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    private final Calculator calculator;

    @Autowired
    public MessageHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    public String handleMessage(OperationMessage operationMessage) {
        switch (operationMessage.getOperation().toLowerCase()){
            case "sum":
                return String.valueOf(calculator.sum(operationMessage.getA(), operationMessage.getB()));
            case "subtraction":
                return String.valueOf(calculator.subtraction(operationMessage.getA(), operationMessage.getB()));
            case "division":
                return String.valueOf(calculator.division(operationMessage.getA(), operationMessage.getB()));
            case "multiplication":
                return String.valueOf(calculator.multiplication(operationMessage.getA(), operationMessage.getB()));
            default:
                return null;
        }
    }
}

