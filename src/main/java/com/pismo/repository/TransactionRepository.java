package com.pismo.repository;

import com.pismo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing Transaction entities.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
