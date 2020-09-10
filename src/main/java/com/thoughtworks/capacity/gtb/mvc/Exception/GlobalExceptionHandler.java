package com.thoughtworks.capacity.gtb.mvc.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserRepetitionException.class,LoginFailedException.class, MethodArgumentNotValidException.class})
    public ResponseEntity invalidParameter(Exception ex) {
        CommonError commonError = new CommonError(HttpStatus.BAD_REQUEST.value(),ex.getMessage());
        return ResponseEntity.badRequest().body(commonError);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity notFound(Exception ex) {
        CommonError commonError = new CommonError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commonError);
    }
}

