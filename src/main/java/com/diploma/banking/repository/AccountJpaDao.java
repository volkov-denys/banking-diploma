package com.diploma.banking.repository;

import com.diploma.banking.model.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountJpaDao implements AccountDao {

    private final AccountRepo accountRepo;

    public AccountJpaDao(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepo.findByNumber(accountNumber);
    }

    @Override
    public Account save(Account account) {
        return accountRepo.save(account);
    }

    @Override
    public void delete(Account account) {
        accountRepo.delete(account);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return accountRepo.findById(Long.valueOf(id));
    }
}
