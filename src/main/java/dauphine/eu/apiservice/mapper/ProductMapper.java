package dauphine.eu.apiservice.mapper;


import dauphine.eu.apiservice.dto.ProductDTO;
import dauphine.eu.apiservice.model.Product;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;

import java.util.Arrays;
import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface   ProductMapper {

     Product asProduct(ProductDTO productDTO);
     ProductDTO asProductDTO(Product product);


     List<ProductDTO> asProductDTO(List<Product> products) ;
}