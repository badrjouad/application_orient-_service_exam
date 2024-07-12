package dauphine.eu.dao;


import dauphine.eu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {



}
