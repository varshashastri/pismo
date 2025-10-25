package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data Transfer Object for creating a transaction request.
 */
public record TransactionRequestDTO(
        @JsonProperty("account_id") Long accountId,
        @JsonProperty("operation_type_id") Long operationTypeId,
        @JsonProperty("amount") Double amount
) {}
