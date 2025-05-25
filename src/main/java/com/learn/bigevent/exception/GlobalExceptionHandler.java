package com.learn.bigevent.exception;


import com.learn.bigevent.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {

        // Log the exception (optional)
        e.printStackTrace();

        // Return a generic error response
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "An unexpected error occurred");
    }

}
