package com.pismo.service;

import com.pismo.enums.OperationTypeEnum;
import com.pismo.entity.Account;
import com.pismo.entity.OperationType;
import com.pismo.entity.Transaction;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationTypeRepository;
import com.pismo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service for managing transactions between accounts.
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    /**
     * Creates a new transaction for a given account and operation type.
     * @param accountId the ID of the account
     * @param operationTypeId the type of operation
     * @param amount the transaction amount
     * @return the created Transaction entity
     * @throws AccountNotFoundException if account is not found
     * @throws OperationTypeNotFoundException if operation type is not found
     */
    @Transactional
    public Transaction createTransaction(Long accountId, Long operationTypeId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        OperationType opType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(() -> new OperationTypeNotFoundException(operationTypeId));

        if (operationTypeId != OperationTypeEnum.PAYMENT.getCode()) {
            amount = -Math.abs(amount);
        }

        Transaction transaction = Transaction.builder()
                .account(account)
                .operationType(opType)
                .amount(amount)
                .eventDate(LocalDateTime.now())
                .build();
        return transactionRepository.save(transaction);
    }
}
