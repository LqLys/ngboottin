package com.example.tinbackend.security.domain.usertravelgroup.repository;

import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import com.example.tinbackend.security.domain.usertravelgroup.repository.fragment.UserTravelGroupRepositoryFragment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTravelGroupRepository extends JpaRepository<UserTravelGroupEntity, Long>, UserTravelGroupRepositoryFragment {

    UserTravelGroupEntity findByTravelGroup_IdAndUser_Id(Long travelGroupId, Long userId);
    List<UserTravelGroupEntity> findAllByTravelGroup_IdIn(List<Long> groupIds);

    void findAllByUserId(Long userId);
}
