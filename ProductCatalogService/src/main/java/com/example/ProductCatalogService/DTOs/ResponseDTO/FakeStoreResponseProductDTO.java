package com.example.ProductCatalogService.DTOs.ResponseDTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FakeStoreResponseProductDTO {
        private long id;
        private String title;
        private double price;
        private String description;
        private String category;
        private String image;

}
