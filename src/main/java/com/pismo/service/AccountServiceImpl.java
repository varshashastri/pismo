package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.DuplicateDocumentNumberException;
import com.pismo.repository.AccountRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for managing account operations.
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public @Schema(description = "The created account entity") Account createAccount(
            @Schema(description = "The unique document number for the account") String documentNumber) {
        if(accountRepository.findByDocumentNumber(documentNumber).isPresent()){
            throw new DuplicateDocumentNumberException("Document number already exists.");
        }
        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        return accountRepository.save(account);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Schema(description = "The retrieved account entity") Account getAccount(
            @Schema(description = "The ID of the account to retrieve") Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
    }
}
