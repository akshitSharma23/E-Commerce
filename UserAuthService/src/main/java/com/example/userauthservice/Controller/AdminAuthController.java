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

    @GetMapping("validate/{id}")
    public ResponseEntity<ValidateResponseDTO> validate(@RequestHeader("AuthToken") String token, @PathVariable long id){
//        System.out.println();
        Optional<ValidateResponseDTO> optional=authService.validate(token,id);
        if(optional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(optional.get(),HttpStatus.ACCEPTED);
    }


    @PostMapping("Logoff")
    public ResponseEntity<?> logOff(@RequestBody LogOffRequestDTO logOffRequestDTO){
//        System.out.println();
        boolean flag=authService.logOff(logOffRequestDTO.getToken(),logOffRequestDTO.getUserid());
        return flag?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
