package com.system.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * GlobalExceptionHandler used for handling global exceptions
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public HttpResponseEntity exceptionHandle(Exception exception){
        // return error message by exception message
        return HttpResponseEntity.error(exception.getMessage());
    }
}


