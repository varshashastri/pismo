package com.pismo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a financial transaction linked to an account and an operation type.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a financial transaction associated with an account and operation type")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("transaction_id")
    @Schema(description = "Unique identifier of the transaction", example = "1001")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @Schema(description = "The account associated with this transaction")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operation_type_id")
    @Schema(description = "The type of operation performed in this transaction")
    private OperationType operationType;

    @Schema(description = "The transaction amount", example = "150.75")
    private BigDecimal amount;

    @Schema(description = "Date and time when the transaction was performed", example = "2025-10-25T14:30:00")
    private LocalDateTime eventDate;
}
