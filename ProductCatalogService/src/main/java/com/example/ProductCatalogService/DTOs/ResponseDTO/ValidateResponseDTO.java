package com.example.ProductCatalogService.DTOs.ResponseDTO;

import com.example.ProductCatalogService.Models.SessionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter@Setter
public class ValidateResponseDTO {
    private List<String> roles;
    private Instant AuthorizationCodeExpiresAt;
    private SessionStatus sessionStatus;
    private boolean isDeleted;
}
