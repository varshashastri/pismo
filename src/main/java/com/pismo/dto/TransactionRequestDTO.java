package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Data Transfer Object for creating a transaction request.
 */
@Schema(description = "Request object for creating a transaction")
public record TransactionRequestDTO(
        @JsonProperty("account_id")
        @Schema(description = "ID of the account for the transaction", example = "1", required = true)
        Long accountId,

        @JsonProperty("operation_type_id")
        @Schema(description = "ID of the operation type (e.g., purchase, payment)", example = "4", required = true)
        Long operationTypeId,

        @JsonProperty("amount")
        @Schema(description = "Transaction amount. Positive for payment, negative for others", example = "100.50", required = true)
        BigDecimal amount
) {}
