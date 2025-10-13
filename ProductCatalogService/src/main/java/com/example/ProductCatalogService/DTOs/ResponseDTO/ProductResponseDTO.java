package com.example.ProductCatalogService.DTOs.ResponseDTO;


import com.example.ProductCatalogService.Models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO implements Serializable {
        private long id;
        private String title;
        private double price;
        private String description;
        private String category;
        private String image;


        public static ProductResponseDTO getInstance(Product product){
            return ProductResponseDTO.builder()
                    .id(product.getId())
                    .title(product.getName())
                    .price(product.getPrice())
                    .description(product.getDescription())
                    .image(product.getImageUrl())
                    .category(product.getCategory().getName())
                    .build();
        }
}
