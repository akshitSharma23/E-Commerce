package com.example.userauthservice.Security.Models;

import com.example.userauthservice.Models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@JsonDeserialize
@NoArgsConstructor
public class GrantedAuthorityIMPL implements GrantedAuthority {

    private String role;
    private String authority;

    public GrantedAuthorityIMPL(String role){
        this.authority=role;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
