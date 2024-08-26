package com.my.product.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.module.FindException;

@ControllerAdvice
public class ProductAdvice {
    //예외를 떠넘긴다.
    /**
     * 상품 FindException
     */
    @ExceptionHandler(FindException.class)
    public ResponseEntity FindException(FindException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
