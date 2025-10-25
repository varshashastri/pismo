package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.DuplicateDocumentNumberException;
import com.pismo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for managing account operations.
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    /**
     * Creates a new account with the given document number.
     * @param documentNumber the unique document number for the account
     * @return the created Account entity
     * @throws DuplicateDocumentNumberException if the document number already exists
     */
    public Account createAccount(String documentNumber) {
        if(accountRepository.findByDocumentNumber(documentNumber).isPresent()){
            throw new DuplicateDocumentNumberException("Document number already exists.");
        }
        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        return accountRepository.save(account);
    }

    /**
     * Retrieves an account by its ID.
     * @param accountId the ID of the account
     * @return the Account entity
     * @throws AccountNotFoundException if no account is found with the given ID
     */
    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
