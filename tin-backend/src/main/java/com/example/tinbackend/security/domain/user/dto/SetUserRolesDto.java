package com.example.tinbackend.security.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetUserRolesDto {
    private String username;
    private List<String> roles;
}
