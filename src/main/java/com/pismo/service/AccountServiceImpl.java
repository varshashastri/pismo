package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.exceptions.AccountNotFoundException;
import com.pismo.exceptions.DuplicateDocumentNumberException;
import com.pismo.repository.AccountRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for managing account operations.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public @Schema(description = "The created account entity") Account createAccount(
            @Schema(description = "The unique document number for the account") String documentNumber) {

        log.info("Creating account with documentNumber={}", documentNumber);

        if (accountRepository.findByDocumentNumber(documentNumber).isPresent()) {
            log.error("Duplicate document number detected: {}", documentNumber);
            throw new DuplicateDocumentNumberException("Document number already exists.");
        }

        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        Account savedAccount = accountRepository.save(account);

        log.info("Account created successfully with ID={} and documentNumber={}",
                savedAccount.getAccountId(), savedAccount.getDocumentNumber());

        return savedAccount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Schema(description = "The retrieved account entity") Account getAccount(
            @Schema(description = "The ID of the account to retrieve") Long accountId) {

        log.info("Fetching account with ID={}", accountId);

        return accountRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.error("Account not found for ID={}", accountId);
                    return new AccountNotFoundException(accountId);
                });
    }
}
