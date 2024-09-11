package com.example.demo.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private BigDecimal amount;
    private String description;
    private LocalDateTime timestamp;

    public Transaction(BigDecimal amount, String description, LocalDateTime timestamp) {
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }

    // Getters et Setters
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

