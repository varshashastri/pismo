//package com.pismo.service;
//
//import com.pismo.entity.Account;
//import com.pismo.exceptions.AccountNotFoundException;
//import com.pismo.exceptions.DuplicateDocumentNumberException;
//import com.pismo.repository.AccountRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//class AccountServiceImplTest {
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @InjectMocks
//    private AccountServiceImpl accountServiceImpl;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createAccount_shouldSaveAccount_whenDocumentNumberIsUnique() {
//        String documentNumber = "12345678900";
//        Account accountToSave = new Account();
//        accountToSave.setDocumentNumber(documentNumber);
//
//        when(accountRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.empty());
//        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Account created = accountServiceImpl.createAccount(documentNumber);
//
//        assertThat(created.getDocumentNumber()).isEqualTo(documentNumber);
//
//        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
//        verify(accountRepository).save(accountCaptor.capture());
//        assertThat(accountCaptor.getValue().getDocumentNumber()).isEqualTo(documentNumber);
//    }
//
//    @Test
//    void createAccount_shouldThrowException_whenDocumentNumberExists() {
//        String documentNumber = "12345678900";
//        Account existingAccount = new Account();
//        existingAccount.setDocumentNumber(documentNumber);
//
//        when(accountRepository.findByDocumentNumber(documentNumber)).thenReturn(Optional.of(existingAccount));
//
//        assertThrows(DuplicateDocumentNumberException.class,
//                () -> accountServiceImpl.createAccount(documentNumber));
//
//        verify(accountRepository, never()).save(any(Account.class));
//    }
//
//    @Test
//    void getAccount_shouldReturnAccount_whenAccountExists() {
//        Long accountId = 1L;
//        Account account = new Account();
//
//        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
//
//        Account result = accountServiceImpl.getAccount(accountId);
//
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    void getAccount_shouldThrowException_whenAccountDoesNotExist() {
//        Long accountId = 1L;
//
//        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());
//
//        assertThrows(AccountNotFoundException.class,
//                () -> accountServiceImpl.getAccount(accountId));
//    }
//}
