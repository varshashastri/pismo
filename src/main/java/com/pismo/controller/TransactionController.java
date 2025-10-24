package com.pismo.controller;

import com.pismo.dto.TransactionRequestDTO;
import com.pismo.dto.TransactionResponseDTO;
import com.pismo.entity.Transaction;
import com.pismo.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO dto) {
        Transaction transaction = transactionService.createTransaction(
                dto.accountId(),
                dto.operationTypeId(),
                dto.amount()
        );

        TransactionResponseDTO response = new TransactionResponseDTO(
                transaction.getTransactionId(),
                transaction.getAccount().getAccountId(),
                transaction.getOperationType().getOperationTypeId(),
                transaction.getAmount()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
