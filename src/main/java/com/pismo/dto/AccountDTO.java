package com.pismo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AccountDTO(Long accountId, @NotBlank(message = "Document number is required") @JsonProperty("document_number") String documentNumber) {}
