package com.example.userauthservice.Controller;

import com.example.userauthservice.DTOs.LogOffRequestDTO;
import com.example.userauthservice.DTOs.ValidateResponseDTO;
import com.example.userauthservice.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("Auth")
public class AdminAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("UpdateRole")
    public void updateRole(){

    }

    @GetMapping("{id}")
    public void UserRole(long id){

    }


}
