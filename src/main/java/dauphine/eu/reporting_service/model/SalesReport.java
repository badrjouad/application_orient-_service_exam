package dauphine.eu.reporting_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SalesReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private Long timesSold;
    private Double revenue;
    public SalesReport(Long productId, String productName, Long timesSold, Double revenue) {
        this.productId = productId;
        this.productName = productName;
        this.timesSold = timesSold;
        this.revenue = revenue;
    }
}
