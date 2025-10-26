package com.pismo.exceptions;

/**
 * Exception thrown when an operation type with the given ID is not found.
 */
public class OperationTypeNotFoundException extends RuntimeException {
    public OperationTypeNotFoundException(final Long operationTypeId) {
        super("Operation type with ID " + operationTypeId + " not found.");
    }
}
