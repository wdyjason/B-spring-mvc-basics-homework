package com.thoughtworks.capacity.gtb.mvc.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistedException.class)
    public ResponseEntity userAlreadyExistsError(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
