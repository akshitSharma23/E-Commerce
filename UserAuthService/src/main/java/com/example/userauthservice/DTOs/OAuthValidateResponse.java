package com.example.userauthservice.DTOs;

import com.example.userauthservice.Models.SessionStatus;
import com.example.userauthservice.Security.Models.Authorization;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter@Setter
@Builder
public class OAuthValidateResponse {
    private boolean isDeleted;
    private SessionStatus sessionStatus;
    private Instant AuthorizationCodeExpiresAt;

    public static OAuthValidateResponse getInstance(Authorization authorization){
        return OAuthValidateResponse.builder()
                .AuthorizationCodeExpiresAt(authorization.getAuthorizationCodeExpiresAt())
                .isDeleted(authorization.isDeleted())
                .sessionStatus(authorization.getSessionStatus()).build();
    }
}
