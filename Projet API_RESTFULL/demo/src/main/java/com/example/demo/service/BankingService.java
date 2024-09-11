package com.example.demo.service;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.entity.Account;
import com.example.demo.entity.Transaction;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankingService {
    private Map<Long, Account> accounts = new HashMap<>();
    private Map<Long, List<
            Transaction>> transactions = new HashMap<>();

    // Méthode pour consulter le solde
    public BigDecimal getAccountBalance(Long accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new ResourceNotFoundException("Compte non trouvé");
        }
        return account.getBalance();
    }

    // Méthode pour obtenir l'historique des transactions avec pagination
    public List<Transaction> getTransactionHistory(Long accountId, int page, int size) {
        List<Transaction> allTransactions = transactions.getOrDefault(accountId, new ArrayList<>());
        int fromIndex = Math.min(page * size, allTransactions.size());
        int toIndex = Math.min((page + 1) * size, allTransactions.size());
        return allTransactions.subList(fromIndex, toIndex);
    }

    // Méthode pour effectuer un virement
    public void transferFunds(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);
        if (fromAccount == null || toAccount == null) {
            throw new ResourceNotFoundException("Compte non trouvé");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        // Ajouter les transactions
        transactions.computeIfAbsent(fromAccountId, k -> new ArrayList<>())
                .add(new Transaction(amount.negate(), "Virement vers " + toAccountId, LocalDateTime.now()));
        transactions.computeIfAbsent(toAccountId, k -> new ArrayList<>())
                .add(new Transaction(amount, "Virement de " + fromAccountId, LocalDateTime.now()));
    }

    // Initialiser des comptes pour la démonstration
    @PostConstruct
    public void init() {
        Account account1 = new Account(1L, new BigDecimal("1000.00"), "USD");
        Account account2 = new Account(2L, new BigDecimal("2000.00"), "USD");
        Account account3 = new Account(3L, new BigDecimal("3000.00"), "USD");
        Account account4 = new Account(4L, new BigDecimal("4000.00"), "USD");
        Account account5 = new Account(5L, new BigDecimal("5000.00"), "USD");
        Account account6 = new Account(6L, new BigDecimal("6000.00"), "USD");
        accounts.put(1L, account1);
        accounts.put(2L, account2);
        accounts.put(3L, account3);
        accounts.put(4L, account4);
        accounts.put(5L, account5);
        accounts.put(6L, account6);
    }
}
