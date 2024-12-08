package com.diploma.banking.repository;

import com.diploma.banking.model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountDao implements AccountDao {

    private static Long idCounter = 0L;
    private final Map<Long, Account> db = new HashMap<>();

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        for (Account account : db.values()) {
            if (account.getNumber().equals(accountNumber)) {
                return Optional.of(account);
            }
        }
        return Optional.empty();
    }

    @Override
    public Account save(Account account) {
        account.setId(idCounter);
        db.put(idCounter, account);
        return db.get(idCounter++);
    }

    @Override
    public void delete(Account account) {
        db.remove(account.getId());
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return Optional.ofNullable(db.get(Long.valueOf(id)));
    }
}
