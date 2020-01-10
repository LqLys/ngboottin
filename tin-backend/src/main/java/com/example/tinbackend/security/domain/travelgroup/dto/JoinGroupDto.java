package com.example.tinbackend.security.domain.travelgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinGroupDto {

    @NotNull
    private Long groupId;
    @NotBlank
    private String username;
}
