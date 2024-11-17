package com.diploma.banking.model.dto.input;

import com.diploma.banking.model.Account;
import jakarta.validation.constraints.NotNull;

public record AccountInput(
        @NotNull
        Integer userId,
        Account.AccountType type
) {
}
