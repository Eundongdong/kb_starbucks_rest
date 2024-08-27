package com.my.order.advice;

import com.my.order.exception.AddException;
import com.my.order.exception.FindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderAdvice {
    @ExceptionHandler(FindException.class)
    public ResponseEntity FindException(FindException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(AddException.class)
    public ResponseEntity AddException(AddException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
