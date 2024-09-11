package apisoap.example.Api_soap.modele;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement
public class Transaction {
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;

    // Constructeurs
    public Transaction() {}

    public Transaction(BigDecimal amount, String description, LocalDateTime date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
