package com.diploma.banking.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    private Long id;
    private String number;
    private AccountType type;
    private Double amount;
    private Double apy;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Deprecated
    protected Account() {}

    public Account(String number, AccountType type, Double amount, Double apy, LocalDate createdAt, User user) {
        this.type = type;
        this.amount = amount;
        this.apy = apy;
        this.createdAt = createdAt;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public AccountType getType() {
        return type;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getApy() {
        return apy;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setApy(Double apy) {
        this.apy = apy;
    }

    public enum AccountType {
        SAVINGS,
        CHECKING;
    }
}
