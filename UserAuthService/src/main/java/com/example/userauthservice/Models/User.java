package com.example.userauthservice.Models;

import com.example.userauthservice.DTOs.UserRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User extends BaseModel {

    private String email;

    private String password;

    @Column(name="full_name")
    private String fullName;

    @ManyToMany(fetch= FetchType.LAZY)
    private List<Role> roles=new ArrayList<>();

    public static User getInstance(UserRequest userRequest){
        User user=User.builder()
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .fullName(userRequest.getFullName())
                .build();
        return user;
    }
}
