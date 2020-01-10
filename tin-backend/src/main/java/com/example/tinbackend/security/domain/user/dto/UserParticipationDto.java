package com.example.tinbackend.security.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserParticipationDto {

    private Long id;
    private String username;
    private String email;
    private BigDecimal participation;
}
