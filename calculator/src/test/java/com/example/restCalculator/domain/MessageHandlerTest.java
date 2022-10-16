package com.example.restCalculator.domain;

import com.example.restCalculator.domain.calculator.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MessageHandlerTest {

    @InjectMocks
    MessageHandler messageHandler;

    @Mock
    Calculator calculator;

    @BeforeEach
    public void setup() {
        openMocks(this);
        messageHandler = new MessageHandler(calculator);
    }

    @ParameterizedTest
    @MethodSource("provideOperationSumMessages")
    public void shouldRedirectSumResultToSumOperation(OperationMessage operationMessage){
        when(calculator.sum(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(BigDecimal.valueOf(2));

        messageHandler.handleMessage(operationMessage);

        verify(calculator, times(1)).sum(any(BigDecimal.class), any(BigDecimal.class));
    }

    @ParameterizedTest
    @MethodSource("provideOperationSubtractionMessages")
    public void shouldRedirectSubtractionResultToSubtractOperation(OperationMessage operationMessage){
        when(calculator.subtraction(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(BigDecimal.valueOf(2));

        messageHandler.handleMessage(operationMessage);

        verify(calculator, times(1)).subtraction(any(BigDecimal.class), any(BigDecimal.class));

    }

    @ParameterizedTest
    @MethodSource("provideOperationDivisionMessages")
    public void shouldRedirectDivisionResultToDivisionOperation(OperationMessage operationMessage){
        when(calculator.division(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(BigDecimal.valueOf(2));

        messageHandler.handleMessage(operationMessage);

        verify(calculator, times(1)).division(any(BigDecimal.class), any(BigDecimal.class));

    }

    @ParameterizedTest
    @MethodSource("provideOperationMultiplicationMessages")
    public void shouldRedirectMultiplicationResultToMultiplicationOperation(OperationMessage operationMessage){
        when(calculator.multiplication(any(BigDecimal.class), any(BigDecimal.class))).thenReturn(BigDecimal.valueOf(2));

        messageHandler.handleMessage(operationMessage);

        verify(calculator, times(1)).multiplication(any(BigDecimal.class), any(BigDecimal.class));

    }


    private static Stream<Arguments> provideOperationSumMessages() {
        return Stream.of(
                Arguments.of(new OperationMessage("sum", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("Sum", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("SUM", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("SuM", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("sUM", BigDecimal.valueOf(1),BigDecimal.valueOf(1)))
        );
    }

    private static Stream<Arguments> provideOperationSubtractionMessages() {
        return Stream.of(
                Arguments.of(new OperationMessage("subtraction", BigDecimal.valueOf(1), BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("Subtraction", BigDecimal.valueOf(1), BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("SUBTRACTION", BigDecimal.valueOf(1), BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("SUBtractION", BigDecimal.valueOf(1), BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("sUBTRACTION", BigDecimal.valueOf(1), BigDecimal.valueOf(1)))
        );
    }

    private static Stream<Arguments> provideOperationDivisionMessages() {
        return Stream.of(
                Arguments.of(new OperationMessage("division", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("Division", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("DIVISION", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("DIVIsiON", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("dIVISION", BigDecimal.valueOf(1),BigDecimal.valueOf(1)))
        );
    }

    private static Stream<Arguments> provideOperationMultiplicationMessages() {
        return Stream.of(
                Arguments.of(new OperationMessage("multiplication", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("Multiplication", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("MULTIPLICATION", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("MULTiplicaTION", BigDecimal.valueOf(1),BigDecimal.valueOf(1))),
                Arguments.of(new OperationMessage("mULTIPLICATION", BigDecimal.valueOf(1),BigDecimal.valueOf(1)))
        );
    }

}