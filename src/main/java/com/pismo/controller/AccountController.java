package com.pismo.controller;

import com.pismo.dto.AccountDTO;
import com.pismo.entity.Account;
import com.pismo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing account-related endpoints.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Tag(name = "Accounts", description = "Endpoints for managing accounts")
@Slf4j
public class AccountController {

    private final AccountService accountServiceImpl;

    /**
     * Creates a new account with the provided document number.
     *
     * @param request account data transfer object
     * @return the created account as DTO
     */
    @PostMapping
    @Operation(
            summary = "Create a new account",
            description = "Creates a new account using the provided document number"
    )
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid final AccountDTO request) {
        log.info("Received request to create account with documentNumber={}", request.documentNumber());
        final Account account = accountServiceImpl.createAccount(request.documentNumber());
        log.info("Account created successfully with ID={} and documentNumber={}",
                account.getAccountId(), account.getDocumentNumber());
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the ID of the account
     * @return the found account as DTO
     */
    @GetMapping("/{accountId}")
    @Operation(
            summary = "Get account by ID",
            description = "Retrieves account information by its ID"
    )
    public ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "accountId") final Long accountId) {
        log.info("Received request to get account with ID={}", accountId);
        final Account account = accountServiceImpl.getAccount(accountId);
        log.info("Account retrieved successfully with ID={} and documentNumber={}",
                account.getAccountId(), account.getDocumentNumber());
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }
}
