package com.pismo.service.strategy;

import java.math.BigDecimal;

/**
 * Operation strategy for incoming transactions
 */
public interface OperationStrategy {
    BigDecimal apply(BigDecimal amount);
}
