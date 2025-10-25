package com.pismo.repository;

import com.pismo.entity.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing OperationType entities.
 */
public interface OperationTypeRepository extends JpaRepository<OperationType, Long> {
}
