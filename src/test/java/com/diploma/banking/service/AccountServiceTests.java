package com.diploma.banking.service;

import com.diploma.banking.model.Account;
import com.diploma.banking.model.User;
import com.diploma.banking.model.dto.input.AccountInput;
import com.diploma.banking.model.dto.input.AccountUpdateInput;
import com.diploma.banking.repository.AccountDao;
import com.diploma.banking.repository.InMemoryAccountDao;
import com.redis.U;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AccountServiceTests {

    private final static AccountDao accountDao = new InMemoryAccountDao();
    private final static UserService userService = Mockito.mock(UserService.class);
    private final static AccountService service = new AccountService(
            accountDao,
            userService
    );

    private final static Integer NON_EXISTENT_USER_ID = 11;
    private final static Integer USER_ID = 10;
    private final static User FAKE_USER = new User(
            "placeholder",
            "placeholder",
            "placeholder",
            "placeholder",
            "placeholder",
            LocalDate.now(),
            LocalDate.now()
    );

    @BeforeAll
    static void init() {
        when(userService.fetchById(NON_EXISTENT_USER_ID)).thenThrow(new IllegalArgumentException());
        when(userService.fetchById(USER_ID)).thenReturn(FAKE_USER);
    }

    @Test
    void createSavingsAccount() {
        AccountInput input = new AccountInput(
          USER_ID,
          Account.AccountType.SAVINGS
        );

        Account account = service.createAccount(input);

        assertThat(account)
                .extracting(Account::getAmount, Account::getType, Account::getUser, Account::getApy, Account::getNumber)
                .containsExactly(0d, Account.AccountType.SAVINGS, FAKE_USER, 0.07d, any());
    }

    @Test
    void createCheckingAccount() {
        AccountInput input = new AccountInput(
                USER_ID,
                Account.AccountType.CHECKING
        );

        Account account = service.createAccount(input);

        assertThat(account)
                .extracting(Account::getAmount, Account::getType, Account::getUser, Account::getApy, Account::getNumber)
                .containsExactly(0d, Account.AccountType.CHECKING, FAKE_USER, null, any());
    }

    @Test
    void createAccountWithNonExistentUser() {
        AccountInput input = new AccountInput(
                NON_EXISTENT_USER_ID,
                Account.AccountType.CHECKING
        );

        assertThatThrownBy(() -> service.createAccount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void updateAccount() {
        // presave account
        Account toSaveAccount = new Account(
                UUID.randomUUID().toString(),
                Account.AccountType.SAVINGS,
                0d,
                0d,
                LocalDate.now(),
                FAKE_USER
        );
        Account account = accountDao.save(toSaveAccount);
        AccountUpdateInput input = new AccountUpdateInput(
                100d,
                0d
        );

        Account updatedAccount = service.updateAccount(input, Math.toIntExact(account.getId()));

        assertThat(updatedAccount)
                .extracting(Account::getAmount)
                .isEqualTo(100d);
    }

    @Test
    void deleteAccount() {
        // presave account
        Account toSaveAccount = new Account(
                UUID.randomUUID().toString(),
                Account.AccountType.SAVINGS,
                0d,
                0d,
                LocalDate.now(),
                FAKE_USER
        );
        Account account = accountDao.save(toSaveAccount);

        service.deleteAccountById(Math.toIntExact(account.getId()));

        assertThatThrownBy(() -> service.getAccountById(Math.toIntExact(account.getId())))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getAccountById() {
        // presave account
        Account toSaveAccount = new Account(
                UUID.randomUUID().toString(),
                Account.AccountType.SAVINGS,
                0d,
                0d,
                LocalDate.now(),
                FAKE_USER
        );
        Account testableAccount = accountDao.save(toSaveAccount);

        Account account = service.getAccountById(Math.toIntExact(testableAccount.getId()));

        assertThat(account)
                .isNotNull();
    }

    @Test
    void getAccountByNumber() {
        // presave account
        AccountInput input = new AccountInput(
                USER_ID,
                Account.AccountType.CHECKING
        );
        Account testableAccount = service.createAccount(input);

        Account account = service.getAccountByNumber(testableAccount.getNumber());

        assertThat(account)
                .isNotNull();
    }
}
