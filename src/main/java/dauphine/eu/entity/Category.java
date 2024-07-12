package dauphine.eu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    private Long id;
    private String name;
}
