package com.diploma.banking.service;

import com.diploma.banking.model.Account;
import com.diploma.banking.model.Transaction;
import com.diploma.banking.model.dto.input.TransactionInput;
import com.diploma.banking.repository.AccountDao;
import com.diploma.banking.repository.TransactionDao;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;
    private final AccountDao accountDao;
    private final AccountService accountService;

    public TransactionService(TransactionDao transactionDao, AccountDao accountDao, AccountService accountService) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
        this.accountService = accountService;
    }

    public Transaction createTransaction(TransactionInput transactionInput) {
        Account senderAccount = accountService.getAccountByNumber(transactionInput.senderAccountNumber());
        Account receiveraccount = accountService.getAccountByNumber(transactionInput.receiverAccountNumber());
        Transaction transaction = new Transaction(
                transactionInput.title(),
                Transaction.TransactionStatus.COMPLETED,
                transactionInput.amount(),
                LocalDate.now(), // for better control 'Clock' can be used
                senderAccount,
                receiveraccount
        );

        // can be extracted to separate listener with introduction of Spring transactions
        updateAccountOnTransaction(transactionInput.amount(), senderAccount, receiveraccount);

        return transactionDao.save(transaction);
    }

    public Transaction fetchTransaction(Integer id) {
        return transactionDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found for id " + id));
    }

    private void updateAccountOnTransaction(Double amount, Account senderAccount, Account receiverAccount) {
        senderAccount.setAmount(senderAccount.getAmount() - amount);
        receiverAccount.setAmount(receiverAccount.getAmount() + amount);
        accountDao.save(senderAccount);
        accountDao.save(receiverAccount);
    }
}
