package com.precamp.shop.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Void> handleIllegalStateException(RuntimeException e) {
        return ApiResponse.success(null, e.getMessage());
    }
}
