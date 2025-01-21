package com.rate.common.login.util;

import groovy.util.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<String> handleResourceNotFound(HttpStatus status, String param) {
        return ResponseEntity
                .status(status)
                .body(param);
    }
}
