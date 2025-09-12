package com.example.ProductCatalogService.DTOs.RequestDTO;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FakeStoreRequestProductDTO {
        private long id;
        private String title;
        private double price;
        private String description;
        private String category;
        private String image;

}
