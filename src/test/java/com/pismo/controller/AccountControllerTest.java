package com.pismo.controller;

import com.pismo.dto.AccountDTO;
import com.pismo.entity.Account;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount_Success() {
        String documentNumber = "12345678900";
        Account mockAccount = new Account();
        mockAccount.setAccountId(1L);
        mockAccount.setDocumentNumber(documentNumber);

        when(accountService.createAccount(documentNumber)).thenReturn(mockAccount);

        AccountDTO requestDto = new AccountDTO(null, documentNumber);

        ResponseEntity<AccountDTO> response = accountController.createAccount(requestDto);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockAccount.getAccountId(), response.getBody().accountId());
        assertEquals(documentNumber, response.getBody().documentNumber());

        verify(accountService, times(1)).createAccount(documentNumber);
    }

    @Test
    void testGetAccount_Success() {
        Long accountId = 1L;
        String documentNumber = "12345678900";

        Account mockAccount = new Account();
        mockAccount.setAccountId(accountId);
        mockAccount.setDocumentNumber(documentNumber);

        when(accountService.getAccount(accountId)).thenReturn(mockAccount);

        ResponseEntity<AccountDTO> response = accountController.getAccount(accountId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(accountId, response.getBody().accountId());
        assertEquals(documentNumber, response.getBody().documentNumber());

        verify(accountService, times(1)).getAccount(accountId);
    }

    @Test
    void testGetAccount_NotFound() {
        // Arrange
        Long accountId = 99L;
        when(accountService.getAccount(accountId)).thenThrow(new AccountNotFoundException(accountId));

        assertThrows(
                AccountNotFoundException.class,
                () -> accountController.getAccount(accountId)
        );

        verify(accountService, times(1)).getAccount(accountId);
    }
}
