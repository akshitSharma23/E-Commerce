package com.example.ProductCatalogService.Models;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Category extends BaseModel implements Serializable {

    private String name;
    private String description;
    private List<Product> products;


}
