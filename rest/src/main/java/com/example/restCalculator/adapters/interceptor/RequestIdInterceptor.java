package com.example.restCalculator.adapters.interceptor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestIdInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestIdInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        response.addHeader("x-request-id", String.valueOf(UUID.randomUUID()));
        List<String> headers = (List<String>) response.getHeaderNames();
        logger.debug("Status code: {}", response.getStatus());

        headers.stream().distinct().collect(Collectors.toMap(
                        Function.identity(),
                        response::getHeader))
                .forEach((header, value) -> logger.debug("{}: {}", header, value));
        return true;
    }
}
