package com.pismo.service.strategy;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Default operation strategy that negates amounts for non-payment operations.
 */
@Component("DEFAULT")
@Schema(description = "Default operation strategy that ensures transaction amounts are negative")
public class DefaultOperationStrategy implements OperationStrategy {

    /**
     * Applies the default operation strategy.
     * @param amount The transaction amount
     * @return The negated absolute amount (always negative)
     */
    @Override
    public BigDecimal apply(@Schema(description = "Transaction amount to be processed") BigDecimal amount) {
        return amount.abs().negate();
    }
}
