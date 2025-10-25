package com.pismo.controller;

import com.pismo.dto.TransactionRequestDTO;
import com.pismo.dto.TransactionResponseDTO;
import com.pismo.entity.Account;
import com.pismo.entity.OperationType;
import com.pismo.entity.Transaction;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.OperationTypeNotFoundException;
import com.pismo.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction_Success() {
        Long accountId = 1L;
        Long operationTypeId = 2L;
        Double amount = 100.0;

        TransactionRequestDTO requestDTO = new TransactionRequestDTO(accountId, operationTypeId, amount);

        Account mockAccount = new Account();
        mockAccount.setAccountId(accountId);

        OperationType mockOperationType = new OperationType();
        mockOperationType.setOperationTypeId(operationTypeId);

        Transaction mockTransaction = new Transaction();
        mockTransaction.setTransactionId(10L);
        mockTransaction.setAccount(mockAccount);
        mockTransaction.setOperationType(mockOperationType);
        mockTransaction.setAmount(amount);

        when(transactionService.createTransaction(accountId, operationTypeId, amount))
                .thenReturn(mockTransaction);

        ResponseEntity<?> response = transactionController.createTransaction(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        verify(transactionService, times(1)).createTransaction(accountId, operationTypeId, amount);
    }

    @Test
    void testCreateTransaction_AccountNotFound() {
        Long accountId = 1L;
        Long operationTypeId = 2L;
        Double amount = 100.0;
        TransactionRequestDTO requestDTO = new TransactionRequestDTO(accountId, operationTypeId, amount);

        when(transactionService.createTransaction(accountId, operationTypeId, amount))
                .thenThrow(new AccountNotFoundException(accountId));

        assertThrows(AccountNotFoundException.class, () ->
                transactionController.createTransaction(requestDTO));

        verify(transactionService, times(1)).createTransaction(accountId, operationTypeId, amount);
    }

    @Test
    void testCreateTransaction_OperationTypeNotFound() {
        Long accountId = 1L;
        Long operationTypeId = 2L;
        Double amount = 100.0;
        TransactionRequestDTO requestDTO = new TransactionRequestDTO(accountId, operationTypeId, amount);

        when(transactionService.createTransaction(accountId, operationTypeId, amount))
                .thenThrow(new OperationTypeNotFoundException(operationTypeId));

        assertThrows(OperationTypeNotFoundException.class, () ->
                transactionController.createTransaction(requestDTO));

        verify(transactionService, times(1)).createTransaction(accountId, operationTypeId, amount);
    }
}
