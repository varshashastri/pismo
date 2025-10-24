package com.pismo.exceptions;

public class OperationTypeNotFoundException extends RuntimeException {
    public OperationTypeNotFoundException(Long operationTypeId) {
        super("Operation type with ID " + operationTypeId + " not found.");
    }
}