package com.pismo.service;

import com.pismo.entity.Account;
import com.pismo.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(String documentNumber) {
        if(accountRepository.findByDocumentNumber(documentNumber).isPresent()){
            throw new RuntimeException("Document number already exists.");
        }
        Account account = new Account();
        account.setDocumentNumber(documentNumber);
        return accountRepository.save(account);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found."));
    }
}
