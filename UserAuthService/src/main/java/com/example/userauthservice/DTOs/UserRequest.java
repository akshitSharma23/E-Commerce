package com.example.userauthservice.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

@Getter@Setter
public class UserRequest {
    private String fullName;
    @NonNull
    private String email;
    @NonNull
    private String password;



}
