package com.pismo.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultOperationStrategyTest {

    private DefaultOperationStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new DefaultOperationStrategy();
    }

    @Test
    void apply_shouldReturnNegativeValue_whenAmountIsPositive() {
        BigDecimal amount = new BigDecimal("100.00");

        BigDecimal result = strategy.apply(amount);

        assertThat(result)
                .isEqualByComparingTo(new BigDecimal("-100.00"))
                .as("should convert positive to negative");
    }

    @Test
    void apply_shouldKeepValueNegative_whenAmountIsAlreadyNegative() {
        BigDecimal amount = new BigDecimal("-200.50");

        BigDecimal result = strategy.apply(amount);

        assertThat(result)
                .isEqualByComparingTo(new BigDecimal("-200.50"))
                .as("should remain negative even if already negative");
    }

    @Test
    void apply_shouldReturnZero_whenAmountIsZero() {
        BigDecimal amount = BigDecimal.ZERO;

        BigDecimal result = strategy.apply(amount);

        assertThat(result)
                .isEqualByComparingTo(BigDecimal.ZERO)
                .as("zero should stay zero");
    }

    @Test
    void apply_shouldNotMutateOriginalAmount() {
        BigDecimal amount = new BigDecimal("123.45");

        strategy.apply(amount);

        assertThat(amount)
                .isEqualByComparingTo(new BigDecimal("123.45"))
                .as("original amount should not be mutated");
    }
}
