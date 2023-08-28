package com.solviads.employeeservice.exception;

public class EmployeeCreationOperationException extends RuntimeException {
    private String meseage;

    public EmployeeCreationOperationException(String message) {
        super(message);
    }

}
