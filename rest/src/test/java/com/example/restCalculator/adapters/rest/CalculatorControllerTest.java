package com.example.restCalculator.adapters.rest;

import com.example.restCalculator.adapters.RequestProcessor;
import com.example.restCalculator.domain.OperationMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CalculatorControllerTest {

    @InjectMocks
    CalculatorController calculatorController;

    @Mock
    RequestProcessor requestProcessor;

    @BeforeEach
    public void setup() throws IOException {
        openMocks(this);
        calculatorController = new CalculatorController(requestProcessor);
        OperationMessage testResult = new OperationMessage("test", BigDecimal.valueOf(0), BigDecimal.valueOf(0));
        testResult.setResult("test");
        when(requestProcessor.send(any())).thenReturn(testResult);
    }

    @ParameterizedTest
    @MethodSource("provideSendMessageWhenHTTPRequestArrivedToPerformOperations")
    public void shouldSendMessageWhenHTTPRequestArrivedToPerformSum(BigDecimal a, BigDecimal b) throws IOException {

        calculatorController.sum(a,b);

        verify(requestProcessor, times(1)).send(any());
    }

    @ParameterizedTest
    @MethodSource("provideSendMessageWhenHTTPRequestArrivedToPerformOperations")
    public void shouldSendMessageWhenHTTPRequestArrivedToPerformSubtract(BigDecimal a, BigDecimal b) throws IOException {
        calculatorController.subtraction(a,b);

        verify(requestProcessor, times(1)).send(any());
    }

    @ParameterizedTest
    @MethodSource("provideSendMessageWhenHTTPRequestArrivedToPerformOperations")
    public void shouldSendMessageWhenHTTPRequestArrivedToPerformMultiply(BigDecimal a, BigDecimal b) throws IOException {

        calculatorController.multiplication(a,b);

        verify(requestProcessor, times(1)).send(any());
    }

    @ParameterizedTest
    @MethodSource("provideSendMessageWhenHTTPRequestArrivedToPerformOperations")
    public void shouldSendMessageWhenHTTPRequestArrivedToPerformDivide(BigDecimal a, BigDecimal b) throws IOException {

        calculatorController.division(a,b);

        verify(requestProcessor, times(1)).send(any());
    }

    private static Stream<Arguments> provideSendMessageWhenHTTPRequestArrivedToPerformOperations(){
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(2)),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2)),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(-2)),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(-2))
        );
    }
}