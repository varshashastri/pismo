package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.DuplicateDocumentNumberException;
import jakarta.validation.constraints.NotNull;

public interface AccountService {
    /**
     * Creates a new account with the given document number.
     * @param documentNumber the unique document number for the account
     * @return the created Account entity
     * @throws DuplicateDocumentNumberException if the document number already exists
     */
    Account createAccount(@NotNull String documentNumber);

    /**
     * Retrieves an account by its ID.
     * @param accountId the ID of the account
     * @return the Account entity
     * @throws AccountNotFoundException if no account is found with the given ID
     */
    Account getAccount(@NotNull Long accountId);
}
