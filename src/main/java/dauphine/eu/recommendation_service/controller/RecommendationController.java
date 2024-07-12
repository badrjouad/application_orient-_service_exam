package dauphine.eu.recommendation_service.controller;

import dauphine.eu.recommendation_service.model.Recommendation;
import dauphine.eu.recommendation_service.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationRepository recommendationRepository;

    @GetMapping
    public List<Recommendation> getTopRecommendations(String category) {
        return recommendationRepository.findByCategoryOrderByTimesRecommendedDesc(category);
    }
}

