package com.example.tinbackend.security.domain.expense.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddExpenseDto {

    @NotBlank
    private String title;
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal amount;
    @NotNull
    private String username;
    @NotNull
    private Long groupId;
}
