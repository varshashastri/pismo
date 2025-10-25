package com.pismo.exceptions;

/**
 * Exception thrown when an account with the specified ID does not exist.
 */
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long accountId) {
        super("Account with ID " + accountId + " not found.");
    }
}
