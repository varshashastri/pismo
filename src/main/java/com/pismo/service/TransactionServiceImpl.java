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
import com.pismo.service.strategy.OperationStrategy;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Service for managing transactions between accounts.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    public static final String DEFAULT = "DEFAULT";
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;
    private final Map<String, OperationStrategy> operationStrategies;
    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public @Schema(description = "The created transaction entity") Transaction createTransaction(
            @Schema(description = "ID of the account involved in the transaction") @NotNull final Long accountId,
            @Schema(description = "ID of the operation type for this transaction") @NotNull final Long operationTypeId,
            @Schema(description = "Amount of the transaction") @NotNull final BigDecimal amount) {

        BigDecimal signedAmount;
        log.info("Creating transaction for accountId={}, operationTypeId={}, amount={}", accountId, operationTypeId, amount);

        final Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.error("Account not found for ID {}", accountId);
                    return new AccountNotFoundException(accountId);
                });

        final OperationType opType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(() -> {
                    log.error("Operation type not found for ID {}", operationTypeId);
                    return new OperationTypeNotFoundException(operationTypeId);
                });

        OperationTypeEnum opEnum = OperationTypeEnum.fromCode(operationTypeId);
        if (operationStrategies.containsKey(opEnum.name())) {
            signedAmount = operationStrategies.get(opEnum.name()).apply(amount);
        } else {
            signedAmount = operationStrategies.get(DEFAULT).apply(amount);
        }

        Transaction transaction = Transaction.builder()
                .account(account)
                .operationType(opType)
                .amount(signedAmount)
                .eventDate(LocalDateTime.now())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction created successfully with ID {}", savedTransaction.getTransactionId());

        return savedTransaction;
    }
}
