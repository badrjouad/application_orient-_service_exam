package dauphine.eu.order_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "\"order\"")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long customerId;
    private Double totalAmount;
    private String orderStatus;


}
