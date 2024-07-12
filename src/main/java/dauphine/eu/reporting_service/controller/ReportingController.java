package dauphine.eu.reporting_service.controller;


import dauphine.eu.reporting_service.model.SalesByCategoryReport;
import dauphine.eu.reporting_service.model.SalesReport;
import dauphine.eu.reporting_service.model.TotalRevenue;
import dauphine.eu.reporting_service.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reporting")
public class ReportingController {
    @Autowired
    private ReportRepository reportRepository;


    // Méthode pour obtenir les ventes par catégorie
    @GetMapping("/sales-by-category")
    public List<SalesByCategoryReport> getSalesByCategory() {
        return reportRepository.findSalesByCategory();
    }

    // Méthode pour obtenir le chiffre d'affaires total
    @GetMapping("/total-revenue")
    public TotalRevenue getTotalRevenue() {
        return reportRepository.calculateTotalRevenue();
    }

    // Méthode pour générer des rapports sur les ventes pour une période spécifique
    @GetMapping("/sales-report")
    public List<SalesReport> getSalesReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return reportRepository.findSalesReportByDateRange(startDate, endDate);
    }
    }


