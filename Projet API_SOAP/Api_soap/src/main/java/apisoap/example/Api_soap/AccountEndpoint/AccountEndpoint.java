package apisoap.example.Api_soap.AccountEndpoint;
import apisoap.example.Api_soap.modele.Transaction;
import apisoap.example.Api_soap.service.BankingService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.List;

@Endpoint
public class AccountEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/demo/ws";

    private final BankingService bankingService;

    public AccountEndpoint(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBalanceRequest")
    @ResponsePayload
    public GetBalanceResponse getBalance(@RequestPayload GetBalanceRequest request) {
        BigDecimal balance = bankingService.getBalance(request.getAccountId());
        GetBalanceResponse response = new GetBalanceResponse();
        response.setBalance(balance);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTransactionsRequest")
    @ResponsePayload
    public GetTransactionsResponse getTransactions(@RequestPayload GetTransactionsRequest request) {
        List<Transaction> transactions = bankingService.getTransactions(request.getAccountId(), request.getPageNumber(), request.getPageSize());
        GetTransactionsResponse response = new GetTransactionsResponse();
        response.setTransactions(transactions);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "transferFundsRequest")
    @ResponsePayload
    public TransferFundsResponse transferFunds(@RequestPayload TransferFundsRequest request) {
        String status = bankingService.transferFunds(request.getFromAccountId(), request.getToAccountId(), request.getAmount());
        TransferFundsResponse response = new TransferFundsResponse();
        response.setStatus(status);
        return response;
    }

    @XmlRootElement(name = "getBalanceRequest", namespace = NAMESPACE_URI)
    public static class GetBalanceRequest {
        private Long accountId;

        @XmlElement
        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }
    }

    @XmlRootElement(name = "getBalanceResponse", namespace = NAMESPACE_URI)
    public static class GetBalanceResponse {
        private BigDecimal balance;

        @XmlElement
        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }
    }

    @XmlRootElement(name = "getTransactionsRequest", namespace = NAMESPACE_URI)
    public static class GetTransactionsRequest {
        private Long accountId;
        private int pageNumber;
        private int pageSize;

        @XmlElement
        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        @XmlElement
        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        @XmlElement
        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    @XmlRootElement(name = "getTransactionsResponse", namespace = NAMESPACE_URI)
    public static class GetTransactionsResponse {
        private List<Transaction> transactions;

        @XmlElement
        public List<Transaction> getTransactions() {
            return transactions;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }
    }

    @XmlRootElement(name = "transferFundsRequest", namespace = NAMESPACE_URI)
    public static class TransferFundsRequest {
        private Long fromAccountId;
        private Long toAccountId;
        private BigDecimal amount;

        @XmlElement
        public Long getFromAccountId() {
            return fromAccountId;
        }

        public void setFromAccountId(Long fromAccountId) {
            this.fromAccountId = fromAccountId;
        }

        @XmlElement
        public Long getToAccountId() {
            return toAccountId;
        }

        public void setToAccountId(Long toAccountId) {
            this.toAccountId = toAccountId;
        }

        @XmlElement
        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    @XmlRootElement(name = "transferFundsResponse", namespace = NAMESPACE_URI)
    public static class TransferFundsResponse {
        private String status;

        @XmlElement
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
