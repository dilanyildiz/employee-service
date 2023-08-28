package com.solviads.employeeservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private String message;
    private String path;
    private String code;
    private LocalDateTime dateTime;
}
