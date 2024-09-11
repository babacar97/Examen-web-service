package apisoap.example.Api_soap.service;


import apisoap.example.Api_soap.modele.Account;
import apisoap.example.Api_soap.modele.Transaction;
import apisoap.example.Api_soap.modele.TransferRequest;
import apisoap.example.Api_soap.modele.TransferResponse;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebService(serviceName = "BankingService")
public class BankingService {

    private static Map<Long, Account> accounts = new HashMap<>();
    private static Map<Long, List<Transaction>> transactions = new HashMap<>();

    static {
        // Initialisation des comptes et des transactions pour la démonstration
        Account account1 = new Account(1L, new BigDecimal("1000.00"), "USD");
        Account account2 = new Account(2L, new BigDecimal("2000.00"), "USD");
        accounts.put(account1.getId(), account1);
        accounts.put(account2.getId(), account2);

        transactions.put(1L, new ArrayList<>());
        transactions.put(2L, new ArrayList<>());
    }

    @WebMethod
    public BigDecimal getBalance(Long accountId) {
        Account account = accounts.get(accountId);
        if (account == null) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }
        return account.getBalance();
    }

    @WebMethod
    public List<Transaction> getTransactions(Long accountId, int pageNumber, int pageSize) {
        List<Transaction> allTransactions = transactions.get(accountId);
        if (allTransactions == null) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }

        int fromIndex = Math.max(0, (pageNumber - 1) * pageSize);
        int toIndex = Math.min(fromIndex + pageSize, allTransactions.size());
        return allTransactions.subList(fromIndex, toIndex);
    }

    @WebMethod
    public TransferResponse transferFunds(TransferRequest request) {
        Account fromAccount = accounts.get(request.getFromAccountId());
        Account toAccount = accounts.get(request.getToAccountId());


        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("Account not found with ID: " + request.getFromAccountId() + " or " + request.getToAccountId());
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            return new TransferResponse("FAILURE", "Insufficient funds");
        }

        // Débiter le compte expéditeur
        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        transactions.get(fromAccount.getId()).add(new Transaction(request.getAmount().negate(), "Transfer to " + toAccount.getId(), LocalDateTime.now()));

        // Créditer le compte bénéficiaire
        toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));
        transactions.get(toAccount.getId()).add(new Transaction(request.getAmount(), "Transfer from " + fromAccount.getId(), LocalDateTime.now()));

        return new TransferResponse("SUCCESS", "Transfer completed successfully");
    }
}

