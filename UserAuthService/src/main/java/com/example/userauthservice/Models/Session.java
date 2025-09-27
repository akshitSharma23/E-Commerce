package com.example.userauthservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session extends BaseModel{
    @ManyToOne
    private User user;
    private String token;
    private LocalDateTime expiredAt;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
