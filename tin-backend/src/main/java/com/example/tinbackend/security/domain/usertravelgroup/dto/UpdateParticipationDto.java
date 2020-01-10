package com.example.tinbackend.security.domain.usertravelgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateParticipationDto {

    @NotNull
    private Long id;
    @NotNull
    @DecimalMin(value = "0" ,inclusive = true)
    @DecimalMax(value = "100", inclusive = true)
    private BigDecimal participation;
}
