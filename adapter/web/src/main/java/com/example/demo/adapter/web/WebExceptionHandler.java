package com.example.demo.adapter.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(IllegalStateException.class)
    public ErrorResponse handle(IllegalStateException ex) {
        return errorResponse(ex, HttpStatus.CONFLICT);
    }

    private static ErrorResponse errorResponse(Exception exception, HttpStatus status) {
        return ErrorResponse.builder(exception, status, exception.getMessage())
                .title(exception.getClass().getSimpleName())
                .build();
    }
}
