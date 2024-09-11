package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import com.example.demo.service.BankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankingController {

    private final BankingService bankingService;

    public BankingController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getAccountBalance(@PathVariable Long accountId) {
        BigDecimal balance = bankingService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<Transaction> transactions = bankingService.getTransactionHistory(accountId, page, size);
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<Void> transferFunds(
            @PathVariable Long accountId,
            @RequestParam Long recipientId,
            @RequestParam BigDecimal amount) {
        bankingService.transferFunds(accountId, recipientId, amount);
        return ResponseEntity.ok().build();
    }
}
