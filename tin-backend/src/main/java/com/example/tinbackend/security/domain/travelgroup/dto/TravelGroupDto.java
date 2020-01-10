package com.example.tinbackend.security.domain.travelgroup.dto;

import com.example.tinbackend.security.domain.travelgroup.entity.GroupStatus;
import com.example.tinbackend.security.domain.travelgroup.entity.GroupVisibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelGroupDto {

    private Long id;
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private GroupStatus groupStatus;
}
