package dauphine.eu.apiservice.service;

import dauphine.eu.apiservice.model.Product;
import dauphine.eu.apiservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryName(String categoryName) {
        return productRepository.findByCategoryName(categoryName);
    }


    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    public boolean deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public List<Product> getProductsByPriceLessThan(double price) {
        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsByDescriptionKeyword(String keyword) {
        return productRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

    public List<Product> getProductsByCategoryNameAndPriceLessThan(String categoryName, double price) {
        return productRepository.findByCategoryNameAndPriceLessThan(categoryName, price);
    }

    public List<Product> getProductsByCategoryNameOrderByPriceDesc(String categoryName) {
        return productRepository.findByCategoryNameOrderByPriceDesc(categoryName);
    }

    public Optional<Product> getCheapestProductByCategoryName(String categoryName) {
        return productRepository.findFirstByCategoryNameOrderByPriceAsc(categoryName);
    }


}