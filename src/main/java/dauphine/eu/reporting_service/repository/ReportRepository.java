package dauphine.eu.reporting_service.repository;


import dauphine.eu.reporting_service.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findTop10ByOrderByTimesSoldDesc();
}

