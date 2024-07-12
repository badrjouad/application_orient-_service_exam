package dauphine.eu.payement_process.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Double amount;
    private String paymentStatus;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpiryDate;
    private String cardSecurityCode;
    private String fraudStatus;


}
