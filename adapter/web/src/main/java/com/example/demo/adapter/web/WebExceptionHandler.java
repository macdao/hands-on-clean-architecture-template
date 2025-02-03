package com.example.demo.adapter.web;

import com.example.demo.application.port.in.OrderNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handleException(IllegalStateException ex) {
        return errorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse handleException(ConstraintViolationException ex) {
        return errorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ErrorResponse handleException(OrderNotFoundException ex) {
        return errorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return errorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static ErrorResponse errorResponse(Exception exception, HttpStatus status) {
        return ErrorResponse.builder(exception, status, exception.getMessage())
                .title(exception.getClass().getSimpleName())
                .build();
    }
}
