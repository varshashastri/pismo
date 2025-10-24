package com.pismo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationType {
    @Id
    @JsonProperty("operation_type_id")
    private Long operationTypeId;

    @Column(nullable = false)
    @JsonProperty("description")
    private String description;
}
