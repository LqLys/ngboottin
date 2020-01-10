package com.example.tinbackend.security.domain.travelgroup.dto;

import com.example.tinbackend.security.domain.common.CorrectDateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CorrectDateOrder.List({
        @CorrectDateOrder(first = "startDate", second = "endDate", message = "end date can't be before to date"),
})
public class EditGroupDto {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String destination;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
