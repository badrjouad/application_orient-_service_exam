package dauphine.eu.reporting_service.model;

import lombok.Data;

@Data
public class SalesByCategoryReport {
    private String category;
    private Long timesSold;
    public SalesByCategoryReport(String category, Long timesSold) {
        this.category = category;
        this.timesSold = timesSold;
    }
}
