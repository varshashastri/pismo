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
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Service for managing transactions between accounts.
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public @Schema(description = "The created transaction entity") Transaction createTransaction(
            @Schema(description = "ID of the account involved in the transaction") Long accountId,
            @Schema(description = "ID of the operation type for this transaction") Long operationTypeId,
            @Schema(description = "Amount of the transaction") Double amount) {

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
