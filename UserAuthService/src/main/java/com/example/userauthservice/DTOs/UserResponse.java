package com.example.userauthservice.DTOs;

import com.example.userauthservice.Models.Role;
import com.example.userauthservice.Models.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private long id;
    private String fullName;
    private String email;
    private List<Role> roles;


    public static UserResponse getInstance(User user){
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

}
