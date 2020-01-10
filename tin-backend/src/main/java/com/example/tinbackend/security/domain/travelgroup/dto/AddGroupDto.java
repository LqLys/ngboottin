package com.example.tinbackend.security.domain.travelgroup.dto;

import com.example.tinbackend.security.domain.common.CorrectDateOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CorrectDateOrder.List({
        @CorrectDateOrder(first = "startDate", second = "endDate", message = "end date can't be before start date"),
})
public class AddGroupDto {

    @NotBlank
    private String name;
    @NotBlank
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
}
