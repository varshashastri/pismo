package com.pismo.controller;

import com.pismo.dto.AccountDTO;
import com.pismo.entity.Account;
import com.pismo.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing account-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Creates a new account with the provided document number.
     *
     * @param request account data transfer object
     * @return the created account as DTO
     */
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO request) {
        Account account = accountService.createAccount(request.documentNumber());
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the ID of the account
     * @return the found account as DTO
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "accountId") Long accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }
}
