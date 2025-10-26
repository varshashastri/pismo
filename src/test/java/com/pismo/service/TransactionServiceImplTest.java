package com.pismo.service;

import com.pismo.enums.OperationTypeEnum;
import com.pismo.entity.Account;
import com.pismo.entity.OperationType;
import com.pismo.entity.Transaction;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationTypeRepository;
import com.pismo.repository.TransactionRepository;
import com.pismo.service.strategy.DefaultOperationStrategy;
import com.pismo.service.strategy.OperationStrategy;
import com.pismo.service.strategy.PaymentOperationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @Mock
    private Map<String, OperationStrategy> operationStrategies;

    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_shouldSaveTransaction_withNegativeAmount_forNonPaymentOperation() {
        Long accountId = 1L;
        long operationTypeId = OperationTypeEnum.PURCHASE.getCode();
        BigDecimal amount = new BigDecimal("100.0");

        Account account = new Account();
        OperationType opType = new OperationType();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(opType));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(operationStrategies.get("DEFAULT")).thenReturn(new DefaultOperationStrategy());

        Transaction transaction = transactionServiceImpl.createTransaction(accountId, operationTypeId, amount);

        BigDecimal expectedAmount = amount.negate();

        assertThat(transaction.getAmount()).isEqualTo(expectedAmount);
        assertThat(transaction.getAccount()).isEqualTo(account);
        assertThat(transaction.getOperationType()).isEqualTo(opType);
        assertThat(transaction.getEventDate()).isNotNull();

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(captor.capture());
        assertThat(captor.getValue().getAmount()).isEqualTo(expectedAmount);
    }


    @Test
    void createTransaction_shouldSaveTransaction_withPositiveAmount_forPaymentOperation() {
        Long accountId = 1L;
        long operationTypeId = OperationTypeEnum.PAYMENT.getCode();
        BigDecimal amount = new BigDecimal("150.0");

        Account account = new Account();

        OperationType opType = new OperationType();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(opType));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(operationStrategies.get(any())).thenReturn(new PaymentOperationStrategy());

        Transaction transaction = transactionServiceImpl.createTransaction(accountId, (long)operationTypeId, amount);

        assertThat(transaction.getAmount()).isEqualTo(amount);
    }

    @Test
    void createTransaction_shouldThrowException_whenAccountNotFound() {
        Long accountId = 1L;
        Long operationTypeId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> transactionServiceImpl.createTransaction(accountId, operationTypeId, new BigDecimal("100.0")));

        verify(transactionRepository, never()).save(any());
    }

    @Test
    void createTransaction_shouldThrowException_whenOperationTypeNotFound() {
        Long accountId = 1L;
        Long operationTypeId = 99L;

        Account account = new Account();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(operationTypeId)).thenReturn(Optional.empty());

        assertThrows(OperationTypeNotFoundException.class,
                () -> transactionServiceImpl.createTransaction(accountId, operationTypeId, new BigDecimal("100.0")));

        verify(transactionRepository, never()).save(any());
    }
}
