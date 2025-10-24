package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.entity.OperationType;
import com.pismo.entity.Transaction;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationTypeRepository;
import com.pismo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final OperationTypeRepository operationTypeRepository;

    @Transactional
    public Transaction createTransaction(Long accountId, Long operationTypeId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        OperationType opType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(() -> new RuntimeException("Operation type not found"));

        // Adjust sign according to operation type
        if(operationTypeId != 4) { // not payment
            amount = -Math.abs(amount);
        }

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(opType);
        transaction.setAmount(amount);
        transaction.setEventDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
