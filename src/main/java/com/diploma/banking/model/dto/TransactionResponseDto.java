package com.diploma.banking.model.dto;

import com.diploma.banking.model.Transaction;

import java.time.LocalDate;

public record TransactionResponseDto(
        String title,
        String status,
        Double amount,
        LocalDate createdAt,
        String senderAccountNumber,
        String receiverAccountNumber
) {
    public static TransactionResponseDto from(Transaction transaction) {
        return new TransactionResponseDto(
          transaction.getTitle(),
          transaction.getStatus().name(),
          transaction.getAmount(),
          transaction.getCreatedAt(),
          transaction.getSender().getNumber(),
          transaction.getReceiver().getNumber()
        );
    }
}
