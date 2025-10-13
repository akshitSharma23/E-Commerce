package com.example.userauthservice.DTOs;

import com.example.userauthservice.Models.SessionStatus;
import com.example.userauthservice.Security.Models.Authorization;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter@Setter
@Builder
public class OAuthValidateResponse {
    private boolean isDeleted;
    private SessionStatus sessionStatus;
    private Instant AuthorizationCodeExpiresAt;
    private List<String> roles;

    public static OAuthValidateResponse getInstance(Authorization authorization, List<String> roles){
        return OAuthValidateResponse.builder()
                .AuthorizationCodeExpiresAt(authorization.getAuthorizationCodeExpiresAt())
                .isDeleted(authorization.isDeleted())
                .sessionStatus(authorization.getSessionStatus())
                .roles(roles).build();
    }
}
