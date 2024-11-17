package com.diploma.banking.controller;

import com.diploma.banking.model.dto.TransactionResponseDto;
import com.diploma.banking.model.dto.input.TransactionInput;
import com.diploma.banking.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDto> addTransaction(@RequestBody TransactionInput transaction) {
        return ResponseEntity.ok(
                TransactionResponseDto.from(transactionService.createTransaction(transaction))
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDto> getTransaction(@PathVariable Integer id) {
        return ResponseEntity.ok(
                TransactionResponseDto.from(transactionService.fetchTransaction(id))
        );
    }
}
