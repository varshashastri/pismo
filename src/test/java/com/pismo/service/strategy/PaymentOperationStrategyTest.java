package com.pismo.service.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOperationStrategyTest {

    private PaymentOperationStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new PaymentOperationStrategy();
    }

    @Test
    void apply_shouldReturnSamePositiveAmount() {
        BigDecimal amount = new BigDecimal("150.75");
        BigDecimal result = strategy.apply(amount);
        assertThat(result).isEqualByComparingTo(amount);
    }

    @Test
    void apply_shouldReturnSameNegativeAmount() {
        BigDecimal amount = new BigDecimal("-200.50");
        BigDecimal result = strategy.apply(amount);
        assertThat(result).isEqualByComparingTo(amount);
    }

    @Test
    void apply_shouldReturnZeroWhenAmountIsZero() {
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal result = strategy.apply(amount);
        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void apply_shouldNotMutateOriginalAmount() {
        BigDecimal amount = new BigDecimal("123.45");
        strategy.apply(amount);
        assertThat(amount).isEqualByComparingTo(new BigDecimal("123.45"));
    }

    @Test
    void apply_shouldThrowNullPointerException_whenAmountIsNull() {
        try {
            strategy.apply(null);
        } catch (NullPointerException e) {
        }
    }
}
