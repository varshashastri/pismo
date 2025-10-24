package com.pismo.dto;

import java.time.LocalDateTime;

public record TransactionResponseDTO(
        Long transactionId,
        Long accountId,
        Long operationTypeId,
        Double amount) {}
