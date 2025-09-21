package com.example.ProductCatalogService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {

    private String name;
    private String description;
    @OneToMany(mappedBy="category",fetch = FetchType.LAZY)
    private List<Product> products;


}
