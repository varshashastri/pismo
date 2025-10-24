package com.pismo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class OperationType {
    @Id
    @Getter
    @Setter
    private Long operationTypeId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String description;
}
