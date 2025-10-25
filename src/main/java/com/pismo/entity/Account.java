package com.pismo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a bank account with a unique document number.
 */
@Entity
@Getter
@Setter
@Schema(description = "Bank account entity with unique document number")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("account_id")
    @Schema(description = "Unique identifier of the account", example = "1")
    private Long accountId;

    @Column(unique = true, nullable = false)
    @JsonProperty("document_number")
    @Schema(description = "Unique document number associated with the account", example = "12345678900")
    private String documentNumber;
}
