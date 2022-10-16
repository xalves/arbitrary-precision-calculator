package com.example.restCalculator.adapters.rest;

import com.example.restCalculator.adapters.RequestProcessor;
import com.example.restCalculator.domain.OperationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

@RestController
public class CalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    private final RequestProcessor requestProcessor;

    @Autowired
    public CalculatorController(RequestProcessor requestProcessor){
        this.requestProcessor = requestProcessor;
    }

    @GetMapping(value = "/sum", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> sum(@RequestParam BigDecimal a, @RequestParam BigDecimal b) throws IOException {
        logger.info("Receiving http request operation={}, a={}, b={}", "sum", a, b);
        final OperationMessage sumMessage = new OperationMessage("sum", a, b);
        final OperationMessage result = requestProcessor.send(sumMessage);
        return getResponseEntity(result);
    }

    @GetMapping(value = "/subtraction", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> subtraction(@RequestParam BigDecimal a, @RequestParam BigDecimal b) throws IOException {
        logger.info("Receiving http request operation={}, a={}, b={}", "subtraction", a, b);
        final OperationMessage subtractionMessage = new OperationMessage("subtraction", a, b);
        final OperationMessage result = requestProcessor.send(subtractionMessage);
        return getResponseEntity(result);
    }

    @GetMapping(value = "/multiplication", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> multiplication(@RequestParam BigDecimal a, @RequestParam BigDecimal b) throws IOException {
        logger.info("Receiving http request operation={}, a={}, b={}", "multiplication", a, b);
        final OperationMessage multiplicationMessage = new OperationMessage("multiplication", a, b);
        final OperationMessage result = requestProcessor.send(multiplicationMessage);
        return getResponseEntity(result);
    }

    @GetMapping(value = "/division", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> division(@RequestParam BigDecimal a, @RequestParam BigDecimal b) throws IOException {
        logger.info("Receiving http request operation={}, a={}, b={}", "division", a, b);
        final OperationMessage divisionMessage = new OperationMessage("division", a, b);
        final OperationMessage result = requestProcessor.send(divisionMessage);
        return getResponseEntity(result);
    }

    private ResponseEntity<Object> getResponseEntity(OperationMessage result) {
        return ResponseEntity.ok().body(Map.of(
                "result", result.getResult()));
    }

}
