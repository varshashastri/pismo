package com.pismo.service;

import com.pismo.entity.Transaction;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import jakarta.validation.constraints.NotNull;

public interface TransactionService {
    /**
     * Creates a new transaction for a given account and operation type.
     * @param accountId the ID of the account
     * @param operationTypeId the type of operation
     * @param amount the transaction amount
     * @return the created Transaction entity
     * @throws AccountNotFoundException if account is not found
     * @throws OperationTypeNotFoundException if operation type is not found
     */
    Transaction createTransaction(@NotNull Long accountId, @NotNull Long operationTypeId, @NotNull Double amount);
}
