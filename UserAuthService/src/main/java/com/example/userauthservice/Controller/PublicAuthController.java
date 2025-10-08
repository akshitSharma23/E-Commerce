package com.example.userauthservice.Controller;

import com.example.userauthservice.DTOs.LogOffRequestDTO;
import com.example.userauthservice.DTOs.UserRequest;
import com.example.userauthservice.DTOs.ValidateRequestDTO;
import com.example.userauthservice.DTOs.ValidateResponseDTO;
import com.example.userauthservice.Exception.UserAlreadyExist;
import com.example.userauthservice.Service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("public")
public class PublicAuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody UserRequest userRequest) throws UserAlreadyExist {
        return authService.signup(userRequest.getFullName(),userRequest.getEmail(),userRequest.getPassword());
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest){
        return authService.login(userRequest.getEmail(),userRequest.getPassword());
    }



    @GetMapping("healthCheck")
    public String healthCheck(){
        return "ok";
    }
}
