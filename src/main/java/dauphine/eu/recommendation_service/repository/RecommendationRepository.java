package dauphine.eu.recommendation_service.repository;

import dauphine.eu.recommendation_service.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findTop10ByOrderByTimesRecommendedDesc();
}
