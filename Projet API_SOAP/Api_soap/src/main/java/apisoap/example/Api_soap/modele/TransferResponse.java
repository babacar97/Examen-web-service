package apisoap.example.Api_soap.modele;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransferResponse {
    private String status;
    private String message;

    // Constructeurs
    public TransferResponse() {}

    public TransferResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters et Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
