package com.pismo.dto;

public record TransactionDTO(Long transactionId, Long accountId, Long operationTypeId, Double amount) {}
