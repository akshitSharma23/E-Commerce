package com.example.ProductCatalogService.Models;

import com.example.ProductCatalogService.DTOs.RequestDTO.FakeStoreRequestProductDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Builder
public class Product extends BaseModel {

    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private Category category;

    public static Product getInstance(FakeStoreRequestProductDTO fakeStoreRequestProductDTO){

        Product product =Product.builder()
                 .description(fakeStoreRequestProductDTO.getDescription())
                 .name(fakeStoreRequestProductDTO.getTitle())
                 .imageUrl(fakeStoreRequestProductDTO.getImage())
                 .price(fakeStoreRequestProductDTO.getPrice())
                .build();
        product.setId(fakeStoreRequestProductDTO.getId());
        return product;
    }
}
