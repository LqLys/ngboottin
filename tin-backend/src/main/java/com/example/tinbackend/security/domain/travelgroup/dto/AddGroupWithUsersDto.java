package com.example.tinbackend.security.domain.travelgroup.dto;

import com.example.tinbackend.security.domain.common.CorrectDateOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CorrectDateOrder.List({
        @CorrectDateOrder(first = "startDate", second = "endDate", message = "end date can't be before to date"),
})
public class AddGroupWithUsersDto {
    @NotBlank
    private String name;
    @NotBlank
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> userIds;
}
