package com.diploma.banking.model.dto;

import com.diploma.banking.model.Account;

import java.time.LocalDate;

public record AccountResponseDto(
        String number,
        LocalDate createdAt,
        Account.AccountType type,
        Double amount,
        Double apy
) {
    public static AccountResponseDto from(Account account) {
        return new AccountResponseDto(
                account.getNumber(),
                account.getCreatedAt(),
                account.getType(),
                account.getAmount(),
                account.getApy()
        );
    }
}
