package com.example.tinbackend.security.domain.travelgroup.dto;

import com.example.tinbackend.security.domain.usertravelgroup.entity.GroupRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDetailsMembers {

    private Long userId;
    private String firstName;
    private String lastName;
    private GroupRole groupRole;

}
