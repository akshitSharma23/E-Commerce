package com.example.ProductCatalogService.DTOs.ResponseDTO;

import com.example.ProductCatalogService.Models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ValidateResponseDTO {
    private UserResponseDTO userResponse;
    private SessionStatus sessionStatus;
}
