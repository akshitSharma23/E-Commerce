package com.example.ProductCatalogService.DTOs.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequestDTO {
    private long userid;
    private String token;
}