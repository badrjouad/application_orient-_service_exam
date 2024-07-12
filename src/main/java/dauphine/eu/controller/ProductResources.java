package dauphine.eu.controller;



import dauphine.eu.dto.ProductDTO;
import dauphine.eu.entity.Product;
import dauphine.eu.service.api.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductResources {

    @Autowired
    ProductServiceInterface productServiceInterface;


    @GetMapping(value = "/hello")
    public ResponseEntity helloWorld() throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body("HelloWorld");

    }


    @GetMapping()
    public ResponseEntity<List<ProductDTO>> get()
    {

            return ResponseEntity.status(HttpStatus.OK).body(productServiceInterface.getAll());



    }

    @GetMapping("/{product_id}")
    private Optional<Product> getById(@PathVariable("product_id") Long product_id)
    {
        return productServiceInterface.find(product_id);
    }


    @DeleteMapping("/{product_id}")
    private ResponseEntity<String> delete(@PathVariable("product_id") Long product_id)  {
        try {

            productServiceInterface.delete(product_id);
            return new ResponseEntity<>("resource deleted successfully", HttpStatus.ACCEPTED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    @PostMapping("/product")
    private ProductDTO save(@RequestBody ProductDTO productDTO)
                                                 {
        productServiceInterface.add(productDTO);
        return  productDTO;
    }



    @PutMapping("/{product}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO) {
        try {
            return new ResponseEntity<>(productServiceInterface.update(productDTO), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

}
