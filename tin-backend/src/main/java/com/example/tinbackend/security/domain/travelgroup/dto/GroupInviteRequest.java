package com.example.tinbackend.security.domain.travelgroup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupInviteRequest {

    private Long groupId;
    private String email;
}
