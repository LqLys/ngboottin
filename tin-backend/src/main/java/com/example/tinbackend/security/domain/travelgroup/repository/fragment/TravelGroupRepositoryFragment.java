package com.example.tinbackend.security.domain.travelgroup.repository.fragment;

import com.example.tinbackend.security.domain.travelgroup.dto.GroupDetailsMembers;
import com.example.tinbackend.security.domain.travelgroup.repository.UserGroupsDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.dto.UserParticipationDto;

import java.util.List;

public interface TravelGroupRepositoryFragment {

    List<UserGroupsDto> getUserGroupsByUserId(Long userId);
    List<GroupDetailsMembers> getGroupDetailsMembers(Long groupId);

    List<UserDto> getGroupMembers(Long groupId);

    List<UserParticipationDto> getUsersWithParticipation(Long groupId);
}
