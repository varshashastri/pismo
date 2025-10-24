package com.pismo.controller;

import com.pismo.dto.TransactionDTO;
import com.pismo.entity.Transaction;
import com.pismo.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO request) {
        Transaction transaction = transactionService.createTransaction(
                request.accountId(), request.operationTypeId(), request.amount()
        );
        return ResponseEntity.ok(new TransactionDTO(
                transaction.getTransactionId(),
                transaction.getAccount().getAccountId(),
                transaction.getOperationType().getOperationTypeId(),
                transaction.getAmount()
        ));
    }
}
