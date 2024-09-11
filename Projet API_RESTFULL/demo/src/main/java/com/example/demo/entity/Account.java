package com.example.demo.entity;

import java.math.BigDecimal;

public class Account {
    private Long accountId;
    private BigDecimal balance;
    private String currency;

    public Account(Long accountId, BigDecimal balance, String currency) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
    }

    // Getters et Setters
    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}