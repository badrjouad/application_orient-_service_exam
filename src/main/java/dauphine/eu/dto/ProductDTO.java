package dauphine.eu.dto;

import dauphine.eu.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class ProductDTO {

    private Long id;
    private String description ;
    private double price;
    private String urlImage;
    private Category category;
}
