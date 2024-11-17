package com.diploma.banking.service;

import com.diploma.banking.model.Account;
import com.diploma.banking.model.User;
import com.diploma.banking.model.dto.input.AccountInput;
import com.diploma.banking.model.dto.input.AccountUpdateInput;
import com.diploma.banking.repository.AccountDao;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public class AccountService {

    private final AccountDao accountDao;
    private final UserService userService;

    public AccountService(AccountDao accountDao, UserService userService) {
        this.accountDao = accountDao;
        this.userService = userService;
    }

    public Account getAccountById(Integer id) {
        return accountDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for id: " + id));
    }

    public Account getAccountByNumber(String number) {
        return accountDao.findByAccountNumber(number)
                .orElseThrow(() -> new IllegalArgumentException("Account not found for number: " + number));
    }

    public void deleteAccountById(Integer id) {
        accountDao.delete(getAccountById(id));
    }

    public Account createAccount(AccountInput accountInput) {
        User user = userService.fetchById(accountInput.userId());
        UUID accountNumber = UUID.randomUUID(); // replace with actual account number generation
        Double apy = null;
        if (accountInput.type().equals(Account.AccountType.SAVINGS)) {
            apy = 0.07d;
        }
        Account account = new Account(
                accountNumber.toString(),
                accountInput.type(),
                0d,
                apy,
                LocalDate.now(),
                user
        );

        return accountDao.save(account);
    }

    public Account updateAccount(AccountUpdateInput accountUpdate, Integer accountId) {
        Account account = getAccountById(accountId);
        account.setAmount(
                account.getAmount() +
                        (accountUpdate.amountToChange() != null ? accountUpdate.amountToChange() : 0d)
        );
        account.setApy(accountUpdate.apyToChange() != null ? accountUpdate.apyToChange() : account.getApy());
        return accountDao.save(account);
    }
}
