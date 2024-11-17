package com.diploma.banking.repository;

import com.diploma.banking.model.Account;

import java.util.Optional;

public interface AccountDao {

    Optional<Account> findByAccountNumber(String accountNumber);
    Account save(Account account);
    void delete(Account account);
    Optional<Account> findById(Integer id);
}
