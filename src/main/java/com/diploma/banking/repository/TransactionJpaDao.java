package com.diploma.banking.repository;

import com.diploma.banking.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TransactionJpaDao implements TransactionDao {

    private final TransactionRepo transactionRepo;

    public TransactionJpaDao(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Integer id) {
        return transactionRepo.findById(Long.valueOf(id));
    }
}
