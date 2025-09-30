package com.example.ProductCatalogService.DTOs.ResponseDTO;


import com.example.ProductCatalogService.Models.Role;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    private long id;
    private String fullName;
    private String email;
    List<Role> roles;
}
