package dauphine.eu.apiservice.repository;


import dauphine.eu.apiservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByCategoryName(@Param("name") String name);
    List<Product> findByPriceLessThan(double price);

    List<Product> findByDescriptionContainingIgnoreCase(String keyword);

    List<Product> findByCategoryNameAndPriceLessThan(String categoryName, double price);

    List<Product> findByCategoryNameOrderByPriceDesc(String categoryName);

    Optional<Product> findFirstByCategoryNameOrderByPriceAsc(String categoryName);





}
