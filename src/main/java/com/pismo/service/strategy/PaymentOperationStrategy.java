package com.pismo.service.strategy;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("PAYMENT")
@Schema(description = "Strategy for payment operations that keeps the amount positive")
public class PaymentOperationStrategy extends DefaultOperationStrategy {

    /**
     * Applies the payment operation strategy.
     * @param amount The transaction amount
     * @return The same amount (unchanged)
     */
    @Override
    public BigDecimal apply(@Schema(description = "Amount of the transaction") BigDecimal amount) {
        return amount;
    }
}
