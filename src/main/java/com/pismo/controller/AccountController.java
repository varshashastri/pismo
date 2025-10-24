package com.pismo.controller;

import com.pismo.dto.AccountDTO;
import com.pismo.entity.Account;
import com.pismo.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO request) {
        Account account = accountService.createAccount(request.documentNumber());
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "accountId") Long accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(new AccountDTO(account.getAccountId(), account.getDocumentNumber()));
    }
}
