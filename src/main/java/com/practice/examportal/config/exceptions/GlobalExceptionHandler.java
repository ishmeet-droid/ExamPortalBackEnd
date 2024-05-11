package com.practice.examportal.config.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFound ex) {

        ApiResponse response = new ApiResponse(ex.getMessage(), false);
        return new ResponseEntity<ApiResponse>(
                response,
                HttpStatus.NOT_FOUND);
    }

    // with Map<String, String>
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errors.put(fieldName, errorMessage);
            }

        });

        return new ResponseEntity<Map<String, String>>(
                errors,
                HttpStatus.BAD_REQUEST);
    }

    // Multiple Exceptions.................
    @ExceptionHandler({ DataIntegrityViolationException.class ,Exception.class})
    public ResponseEntity<List<ApiResponse>> handleMultipleExceptions(
            Exception ex) {

        List<ApiResponse> response = new ArrayList<ApiResponse>();
        

       if (ex instanceof DataIntegrityViolationException) {
           // DataIntegrityViolationException ex1 = (DataIntegrityViolationException) ex;
            response.add(new ApiResponse(
                "mapping can't be done check your input",
                 false));
       }

        else {
            response.add(new ApiResponse(
                "something went wrong",
                 false));
        }
         

        return new ResponseEntity<List<ApiResponse>>(
                response,
                HttpStatus.BAD_REQUEST);
    }

   
}
