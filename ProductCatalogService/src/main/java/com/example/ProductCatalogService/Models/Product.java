package com.example.ProductCatalogService.Models;

import com.example.ProductCatalogService.DTOs.RequestDTO.ProductRequestDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends BaseModel {

    private String name;
    private String description;
    private String imageUrl;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
    private Category category;

    public static Product getInstance(ProductRequestDTO fakeStoreRequestProductDTO){
        Category category1=new Category();
        category1.setName(fakeStoreRequestProductDTO.getCategory());
        Product product =Product.builder()
                .category(category1)
                 .description(fakeStoreRequestProductDTO.getDescription())
                 .name(fakeStoreRequestProductDTO.getTitle())
                 .imageUrl(fakeStoreRequestProductDTO.getImage())
                 .price(fakeStoreRequestProductDTO.getPrice())
                .build();
        return product;
    }
}
