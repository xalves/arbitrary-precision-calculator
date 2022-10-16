package com.example.restCalculator.integration;

import com.example.restCalculator.RestApplication;
import com.example.restCalculator.adapters.logging.LoggableDispatcherServlet;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
classes = {IntegrationTestConfig.class, RestApplication.class, LoggableDispatcherServlet.class})
public class RestAppIT {
    @LocalServerPort
    private int port;

    private static final String RESULT_FIELD = "result";

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @ParameterizedTest
    @MethodSource("provideValuesForHTTPRequestsOperationSum")
    public void shouldAddTwoNumbersWhenSumPathIsCalled(BigDecimal a, BigDecimal b, String expectedResult){
        final String SUM_PATH = "/sum?a=" + a + "&b=" + b;

        given()
                .log()
                .all()
                .when()
                .get(SUM_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(RESULT_FIELD, equalTo(expectedResult))
                .header("x-request-id", any(String.class));
    }

    @ParameterizedTest
    @MethodSource("provideValuesForHTTPRequestsOperationSubtract")
    public void shouldSubtractTwoNumbersWhenSubstractionPathIsCalled(BigDecimal a, BigDecimal b, String expectedResult){
        final String SUBSTRACTION_PATH = "/subtraction?a=" + a + "&b=" + b;

        given()
                .log()
                .all()
                .when()
                .get(SUBSTRACTION_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(RESULT_FIELD, equalTo(expectedResult))
                .header("x-request-id", any(String.class));;
    }

    @ParameterizedTest
    @MethodSource("provideValuesForHTTPRequestsOperationMultiply")
    public void shouldMultiplyTwoNumbersWhenMultiplicationPathIsCalled(BigDecimal a, BigDecimal b, String expectedResult){
        final String MULTIPLICATION_PATH = "/multiplication?a=" + a + "&b=" + b;

        given()
                .log()
                .all()
                .when()
                .get(MULTIPLICATION_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(RESULT_FIELD, equalTo(expectedResult))
                .header("x-request-id", any(String.class));;
    }

    @ParameterizedTest
    @MethodSource("provideValuesForHTTPRequestsOperationDivision")
    public void shouldDivideTwoNumbersWhenDivisionPathIsCalled(BigDecimal a, BigDecimal b, String expectedResult){
        final String DIVISION_PATH = "/division?a=" + a + "&b=" + b;

        given()
                .log()
                .all()
                .when()
                .get(DIVISION_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(RESULT_FIELD, equalTo(expectedResult))
                .header("x-request-id", any(String.class));;
    }

    private static Stream<Arguments> provideValuesForHTTPRequestsOperationSum() {
        return Stream.of(
               Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), "3"),
               Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(2), "1"),
               Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(-2), "-3"),
               Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(-2), "-1")
        );
    }

    private static Stream<Arguments> provideValuesForHTTPRequestsOperationSubtract() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), "-1"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(2), "-3"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(-2), "1"),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(-2), "3")
        );
    }

    private static Stream<Arguments> provideValuesForHTTPRequestsOperationMultiply() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), "2"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(2), "-2"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(-2), "2"),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(-2), "-2")
        );
    }

    private static Stream<Arguments> provideValuesForHTTPRequestsOperationDivision() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(2), "0.5"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(2), "-0.5"),
                Arguments.of(BigDecimal.valueOf(-1), BigDecimal.valueOf(-2), "0.5"),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(-2), "-0.5"),
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(3), "0.333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "33333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333" +
                        "3333333333333333333333333333333333334")
        );
    }

}
