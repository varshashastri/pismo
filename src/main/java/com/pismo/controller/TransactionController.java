package com.pismo.controller;

import com.pismo.dto.TransactionRequestDTO;
import com.pismo.dto.TransactionResponseDTO;
import com.pismo.entity.Transaction;
import com.pismo.service.TransactionService;
import com.pismo.service.TransactionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling transaction-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Tag(name = "Transactions", description = "Endpoints for managing account transactions")
public class TransactionController {

    private final TransactionService transactionServiceImpl;

    /**
     * Creates a new transaction for a given account and operation type.
     *
     * @param dto transaction request data
     * @return the created transaction as a response DTO
     */
    @PostMapping
    @Operation(
            summary = "Create a new transaction",
            description = "Creates a new transaction for a specific account with a given operation type and amount"
    )
    public ResponseEntity<TransactionResponseDTO> createTransaction(@RequestBody TransactionRequestDTO dto) {
        Transaction transaction = transactionServiceImpl.createTransaction(
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
