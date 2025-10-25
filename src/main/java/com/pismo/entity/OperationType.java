package com.pismo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a type of operation that can be performed on an account.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Represents the type of operation that can be applied to an account")
public class OperationType {

    @Id
    @JsonProperty("operation_type_id")
    @Schema(description = "Unique identifier for the operation type", example = "1")
    private Long operationTypeId;

    @Column(nullable = false)
    @JsonProperty("description")
    @Schema(description = "Description of the operation type", example = "Payment")
    private String description;
}
