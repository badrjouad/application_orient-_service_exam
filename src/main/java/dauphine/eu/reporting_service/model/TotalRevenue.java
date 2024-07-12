package dauphine.eu.reporting_service.model;

import lombok.Data;

@Data
public class TotalRevenue {

    private Double totalRevenue;
    public TotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
