package dauphine.eu.reporting_service.controller;


import dauphine.eu.reporting_service.model.Report;
import dauphine.eu.reporting_service.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reporting")
public class ReportingController {
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping("/top-products")
    public List<Report> getTopSellingProducts() {
        return reportRepository.findTop10ByOrderByTimesSoldDesc();
    }
}

