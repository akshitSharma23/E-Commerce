package com.example.userauthservice.DTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogOffRequestDTO {
    private long Userid;
    private String token;
}
