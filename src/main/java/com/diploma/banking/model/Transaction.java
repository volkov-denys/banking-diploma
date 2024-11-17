package com.diploma.banking.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    private Long id;
    private String title;
    private TransactionStatus status;
    private Double amount;
    private LocalDate createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sender_account_id")
    private Account sender;
    @ManyToOne(optional = false)
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;

    @Deprecated
    protected Transaction() {}

    public Transaction(String title, TransactionStatus status, Double amount, LocalDate createdAt, Account sender, Account receiver) {
        this.title = title;
        this.status = status;
        this.amount = amount;
        this.createdAt = createdAt;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    // placeholder, status implementation is not covered, so value is always COMPLETED
    public enum TransactionStatus {
        CREATED,
        COMPLETED
    }
}
