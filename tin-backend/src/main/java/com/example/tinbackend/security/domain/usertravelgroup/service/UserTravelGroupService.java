package com.example.tinbackend.security.domain.usertravelgroup.service;

import com.example.tinbackend.security.domain.usertravelgroup.entity.GroupRole;
import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import com.example.tinbackend.security.domain.usertravelgroup.repository.UserTravelGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class UserTravelGroupService {


    private final UserTravelGroupRepository userTravelGroupRepository;

    public UserTravelGroupService(UserTravelGroupRepository userTravelGroupRepository) {
        this.userTravelGroupRepository = userTravelGroupRepository;
    }


    public void createTravelGroup(Long userId, Long travelGroupId) {
        UserTravelGroupEntity userTravelGroup = UserTravelGroupEntity.builder()
                .build();
        userTravelGroupRepository.save(userTravelGroup);
    }

    public UserTravelGroupEntity getUserTravelGroupByGroupId(Long groupId) {
        return userTravelGroupRepository.getOne(groupId);
    }

    public void addUserToGroup(UserTravelGroupEntity userInGroup) {
        userTravelGroupRepository.save(userInGroup);
    }
}
