package com.diploma.banking.controller;

import com.diploma.banking.model.dto.AccountResponseDto;
import com.diploma.banking.model.dto.input.AccountInput;
import com.diploma.banking.model.dto.input.AccountUpdateInput;
import com.diploma.banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable Integer id) {
        return ResponseEntity.ok(
                AccountResponseDto.from(accountService.getAccountById(id))
        );
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Integer id) {
        accountService.deleteAccountById(id);
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountInput account) {
        return ResponseEntity.ok(
                AccountResponseDto.from(accountService.createAccount(account))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponseDto> updateAccount(
            @RequestBody AccountUpdateInput account,
            @PathVariable Integer id) {
        return ResponseEntity.ok(
                AccountResponseDto.from(accountService.updateAccount(account, id))
        );
    }
}
