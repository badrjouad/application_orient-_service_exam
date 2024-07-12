package dauphine.eu.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    private Long id;
    private String description ;
    private double price;
    private String urlImage;
    private String designation;
    private double ventes;
    private int quantity;
    private String utilisation;
    private int rating;
    private boolean inStock;
    @ManyToOne
    private  Category category;


}
