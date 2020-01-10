package com.example.tinbackend.security.domain.usertravelgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveParticipatonDto {

    @NotNull
    private Long id;
}
