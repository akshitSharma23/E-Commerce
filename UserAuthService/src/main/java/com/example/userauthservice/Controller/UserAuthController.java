package com.example.userauthservice.Controller;

import com.example.userauthservice.DTOs.LogOffRequestDTO;
import com.example.userauthservice.DTOs.OAuthValidateResponse;
import com.example.userauthservice.DTOs.ValidateResponseDTO;
import com.example.userauthservice.Service.AuthService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("auth")
public class UserAuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("validate/{id}")
    public ResponseEntity<OAuthValidateResponse> validate(@RequestHeader("Authorization") String token, @PathVariable long id) throws Exception{
        System.out.println();
//        Optional<ValidateResponseDTO> optional=authService.validate(token,id);
        Optional<OAuthValidateResponse> optional=authService.validateOAuth(token,id);
        if(optional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(optional.get(),HttpStatus.ACCEPTED);
    }


    @PostMapping("logoff/{id}")
    public ResponseEntity<?> logOff(@RequestHeader("Authorization") String token, @PathVariable long id) throws Exception{
        System.out.println();
//        boolean flag=authService.logOff(logOffRequestDTO.getToken(),logOffRequestDTO.getUserid());
        boolean flag=authService.logOffOAuth(token,id);
        return flag?new ResponseEntity<>(HttpStatus.OK):new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
