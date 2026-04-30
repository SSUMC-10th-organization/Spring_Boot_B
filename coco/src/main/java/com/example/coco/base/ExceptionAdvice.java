package com.example.coco.base;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleAllException(Exception e) {
        return ApiResponse.onFailure("COMMON500", e.getMessage(), null);
    }
}