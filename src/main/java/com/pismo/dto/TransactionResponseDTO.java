package com.pismo.dto;

/**
 * Data Transfer Object for returning transaction details in a response.
 */
public record TransactionResponseDTO(
        Long transactionId,
        Long accountId,
        Long operationTypeId,
        Double amount) {}
