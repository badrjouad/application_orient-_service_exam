package dauphine.eu.apiservice.controller;

import dauphine.eu.apiservice.model.Product;
import dauphine.eu.apiservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/products")
    public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Optional<Product> product = Optional.ofNullable(productService.getProductById(productId));
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cat")
    public List<Product> getProductsByCategoryName(@RequestParam String categoryName) {
        return productService.getProductsByCategoryName(categoryName);
    }


    @GetMapping("/by_price_less_than")
    public List<Product> getProductsByPriceLessThan(@RequestParam double price) {
        return productService.getProductsByPriceLessThan(price);
    }

    @GetMapping("/byDescriptionKeyword")
    public List<Product> getProductsByDescriptionKeyword(@RequestParam String keyword) {
        return productService.getProductsByDescriptionKeyword(keyword);
    }

    @GetMapping("/byCategoryAndPriceLessThan")
    public List<Product> getProductsByCategoryNameAndPriceLessThan(@RequestParam String categoryName, @RequestParam double price) {
        return productService.getProductsByCategoryNameAndPriceLessThan(categoryName, price);
    }

    @GetMapping("/byCategoryOrderByPriceDesc")
    public List<Product> getProductsByCategoryNameOrderByPriceDesc(@RequestParam String categoryName) {
        return productService.getProductsByCategoryNameOrderByPriceDesc(categoryName);
    }

    @GetMapping("/cheapestByCategory")
    public Optional<Product> getCheapestProductByCategoryName(@RequestParam String categoryName) {
        return productService.getCheapestProductByCategoryName(categoryName);
    }
        }


