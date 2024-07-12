package dauphine.eu.service.api;



import dauphine.eu.dto.ProductDTO;
import dauphine.eu.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {

    List<ProductDTO> getAll();
    Optional<Product> find(Long id);
    void delete(Long id) throws Exception;
    ProductDTO update(ProductDTO productDTO) throws Exception;
    ProductDTO add(ProductDTO productDTO);
}
