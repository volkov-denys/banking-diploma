package com.diploma.banking.repository;

import com.diploma.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Optional<Account> findByNumber(String accountNumber);
}
