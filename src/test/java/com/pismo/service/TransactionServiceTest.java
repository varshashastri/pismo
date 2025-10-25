package com.pismo.service;

import com.pismo.OperationTypeEnum;
import com.pismo.entity.Account;
import com.pismo.entity.OperationType;
import com.pismo.entity.Transaction;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import com.pismo.repository.AccountRepository;
import com.pismo.repository.OperationTypeRepository;
import com.pismo.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTransaction_shouldSaveTransaction_withNegativeAmount_forNonPaymentOperation() {
        Long accountId = 1L;
        int operationTypeId = OperationTypeEnum.PURCHASE.getCode();
        Double amount = 100.0;

        Account account = new Account();

        OperationType opType = new OperationType();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(opType));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction transaction = transactionService.createTransaction(accountId, (long)operationTypeId, amount);

        assertThat(transaction.getAmount()).isEqualTo(-amount);
        assertThat(transaction.getAccount()).isEqualTo(account);
        assertThat(transaction.getOperationType()).isEqualTo(opType);
        assertThat(transaction.getEventDate()).isNotNull();

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(transactionRepository).save(captor.capture());
        assertThat(captor.getValue().getAmount()).isEqualTo(-amount);
    }

    @Test
    void createTransaction_shouldSaveTransaction_withPositiveAmount_forPaymentOperation() {
        Long accountId = 1L;
        int operationTypeId = OperationTypeEnum.PAYMENT.getCode();
        Double amount = 150.0;

        Account account = new Account();

        OperationType opType = new OperationType();

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        when(operationTypeRepository.findById(any())).thenReturn(Optional.of(opType));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Transaction transaction = transactionService.createTransaction(accountId, (long)operationTypeId, amount);

        assertThat(transaction.getAmount()).isEqualTo(amount);
    }

    @Test
    void createTransaction_shouldThrowException_whenAccountNotFound() {
        Long accountId = 1L;
        Long operationTypeId = 1L;

        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class,
                () -> transactionService.createTransaction(accountId, operationTypeId, 100.0));

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
                () -> transactionService.createTransaction(accountId, operationTypeId, 100.0));

        verify(transactionRepository, never()).save(any());
    }
}
