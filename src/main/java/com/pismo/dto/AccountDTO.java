package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for account information.
 */
@Schema(description = "Account data transfer object")
public record AccountDTO(
        @Schema(description = "Unique identifier of the account", example = "1")
        Long accountId,

        @NotBlank(message = "Document number is required")
        @JsonProperty("document_number")
        @Schema(description = "Document number associated with the account", example = "12345678900", required = true)
        String documentNumber
) {}
