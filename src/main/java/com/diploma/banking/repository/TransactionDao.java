package com.diploma.banking.repository;

import com.diploma.banking.model.Transaction;

import java.util.Optional;

public interface TransactionDao {

    Transaction save(Transaction transaction);
    Optional<Transaction> findById(Integer id);
}
