package dauphine.eu.order_service.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Payment {

    private Long id;
    private Long orderId;
    private Double amount;
    private String paymentStatus;


}
