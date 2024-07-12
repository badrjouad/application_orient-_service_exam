package dauphine.eu.reporting_service.repository;


import dauphine.eu.reporting_service.model.Report;
import dauphine.eu.reporting_service.model.SalesByCategoryReport;
import dauphine.eu.reporting_service.model.SalesReport;
import dauphine.eu.reporting_service.model.TotalRevenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT new dauphine.eu.reporting_service.model.SalesByCategoryReport(r.category, SUM(r.timesSold)) FROM Report r GROUP BY r.category")
    List<SalesByCategoryReport> findSalesByCategory();

    @Query("SELECT new dauphine.eu.reporting_service.model.TotalRevenue(SUM(r.revenue)) FROM Report r")
    TotalRevenue calculateTotalRevenue();

    @Query("SELECT new dauphine.eu.reporting_service.model.SalesReport(r.productId, r.productName, SUM(r.timesSold), SUM(r.revenue)) " +
            "FROM Report r WHERE r.saleDate BETWEEN :startDate AND :endDate GROUP BY r.productId, r.productName")
    List<SalesReport> findSalesReportByDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

