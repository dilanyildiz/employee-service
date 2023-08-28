package com.solviads.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeCreationOperationException.class)
    public ResponseEntity<ExceptionResponse> handleExceptions(EmployeeCreationOperationException exception, WebRequest webRequest) {
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage(exception.getMessage());
        response.setCode("T-101");
        response.setPath(webRequest.getContextPath());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
