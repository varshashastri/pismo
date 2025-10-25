package com.pismo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a bank account with a unique document number.
 */
@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("account_id")
    private Long accountId;

    @Column(unique = true, nullable = false)
    @JsonProperty("document_number")
    private String documentNumber;
}
