package com.example.userauthservice.DTOs;

import com.example.userauthservice.Models.Session;
import com.example.userauthservice.Models.SessionStatus;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateResponseDTO {
    private UserResponseDTO userResponse;
    private SessionStatus sessionStatus;


    public static ValidateResponseDTO getInstance(Session session){
        return ValidateResponseDTO.builder()
                .userResponse(UserResponseDTO.getInstance(session.getUser()))
                .sessionStatus(session.getSessionStatus())
                .build();
    }
}
