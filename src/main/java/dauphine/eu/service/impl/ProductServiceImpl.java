package dauphine.eu.service.impl;


import dauphine.eu.dao.ProductRepository;
import dauphine.eu.dto.ProductDTO;
import dauphine.eu.entity.Product;
import dauphine.eu.mapper.ProductMapper;
import dauphine.eu.service.api.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper ;


    @Override
    public List<ProductDTO> getAll() {

        return  productMapper.asProductDTO(productRepository.findAll());

    }

    @Override
    public Optional<Product> find(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(Long id) throws Exception {
        Product product = productRepository.getById(id) ;
        if (product== null)
        {
            throw new Exception("Product not found");
        }
        productRepository.delete(product);
    }

    @Override
    public ProductDTO add(ProductDTO productDTO) {

        Product product = productMapper.asProduct(productDTO);

        return  productMapper.asProductDTO(productRepository.saveAndFlush(product));
    }

    @Override
    public ProductDTO update(ProductDTO productDTO) throws Exception {

        Product product = productMapper.asProduct(productDTO);

        return  productMapper.asProductDTO(productRepository.save(product));
    }


}
