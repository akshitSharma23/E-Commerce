package com.example.userauthservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateRequestDTO {
    private long userid;
    private String token;
}
