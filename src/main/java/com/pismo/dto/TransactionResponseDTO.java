package com.pismo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Data Transfer Object for returning transaction details in a response.
 */
@Schema(description = "Response object for a transaction")
public record TransactionResponseDTO(
        @Schema(description = "Unique ID of the transaction", example = "1001")
        Long transactionId,

        @Schema(description = "ID of the account associated with the transaction", example = "1")
        Long accountId,

        @Schema(description = "ID of the operation type (e.g., purchase, payment)", example = "4")
        Long operationTypeId,

        @Schema(description = "Transaction amount. Positive for payment, negative for others", example = "100.50")
        BigDecimal amount
) {}
