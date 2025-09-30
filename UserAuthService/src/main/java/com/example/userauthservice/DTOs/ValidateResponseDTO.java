package com.example.userauthservice.DTOs;

import com.example.userauthservice.Models.Session;
import com.example.userauthservice.Models.SessionStatus;
import com.example.userauthservice.Models.User;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateResponseDTO {
    private UserResponse userResponse;
    private SessionStatus sessionStatus;

    public static ValidateResponseDTO getInstance(Session session){
        return ValidateResponseDTO.builder()
                .userResponse(UserResponse.getInstance(session.getUser()))
                .sessionStatus(session.getSessionStatus())
                .build();
    }
}
