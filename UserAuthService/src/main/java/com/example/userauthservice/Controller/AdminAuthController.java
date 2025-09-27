package com.example.userauthservice.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Auth")
public class AdminAuthController {

    @PostMapping("UpdateRole")
    public void updateRole(){

    }

    @GetMapping("{id}")
    public void UserRole(long id){

    }

}
