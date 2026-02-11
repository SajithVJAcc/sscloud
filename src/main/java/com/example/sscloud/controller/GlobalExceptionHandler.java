package com.example.sscloud.controller;

import com.example.sscloud.dto.ErrorResponse;
import com.example.sscloud.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleApiException(AppException ex) {
        ErrorResponse response = new ErrorResponse(ex.getErrorCode(), ex.getMessage());
        if(HttpStatus.NOT_FOUND.name().equals(ex.getErrorCode())){
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        else if(HttpStatus.BAD_REQUEST.name().equals(ex.getErrorCode())){
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

        }
        else {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
