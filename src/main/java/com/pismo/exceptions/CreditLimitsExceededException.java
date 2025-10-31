package com.pismo.exceptions;

/**
 * Exception thrown when trying to create an account with a duplicate document number.
 */
public class CreditLimitsExceededException extends RuntimeException {
    public CreditLimitsExceededException(final String message) {
        super(message);
    }
}
