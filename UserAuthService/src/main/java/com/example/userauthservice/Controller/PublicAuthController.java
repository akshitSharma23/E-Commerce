package com.example.userauthservice.Controller;

import com.example.userauthservice.DTOs.LogOffRequestDTO;
import com.example.userauthservice.DTOs.UserRequest;
import com.example.userauthservice.DTOs.ValidateRequestDTO;
import com.example.userauthservice.Exception.UserAlreadyExist;
import com.example.userauthservice.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("validate")
    public ResponseEntity<Boolean> validate(@RequestBody ValidateRequestDTO validateRequestDTO){
        System.out.println();
        Boolean flag=authService.validate(validateRequestDTO.getToken(),validateRequestDTO.getUserid());
        return new ResponseEntity<>(flag,flag? HttpStatus.ACCEPTED:HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("Logoff")
    public ResponseEntity<?> logOff(@RequestBody LogOffRequestDTO logOffRequestDTO){
//        System.out.println();
        boolean flag=authService.logOff(logOffRequestDTO.getToken(),logOffRequestDTO.getUserid());
        return flag?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("healthCheck")
    public String healthCheck(){
        return "ok";
    }
}
