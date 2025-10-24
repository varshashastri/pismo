package com.pismo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long accountId;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String documentNumber;
}
