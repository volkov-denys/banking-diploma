package com.diploma.banking.model.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionInput(
        @NotNull
        @NotEmpty(message = "Title cannot be empty.")
        String title,
        @NotNull
        @Positive
        Double amount,
        @NotNull
        @NotEmpty(message = "Sender account number cannot be empty.")
        String senderAccountNumber,
        @NotNull
        @NotEmpty(message = "Receiver account number cannot be empty.")
        String receiverAccountNumber
) {
}
