package com.pismo.exceptions;

/**
 * Exception thrown when trying to create an account with a duplicate document number.
 */
public class DuplicateDocumentNumberException extends RuntimeException {
    public DuplicateDocumentNumberException(final String message) {
        super(message);
    }
}
