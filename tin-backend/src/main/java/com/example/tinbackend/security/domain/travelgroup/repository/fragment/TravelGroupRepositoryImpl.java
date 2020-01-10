package com.example.tinbackend.security.domain.travelgroup.repository.fragment;

import com.example.tinbackend.security.domain.travelgroup.dto.GroupDetailsMembers;
import com.example.tinbackend.security.domain.travelgroup.entity.QTravelGroupEntity;
import com.example.tinbackend.security.domain.travelgroup.repository.UserGroupsDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.dto.UserParticipationDto;
import com.example.tinbackend.security.domain.user.entity.QUserEntity;
import com.example.tinbackend.security.domain.usertravelgroup.entity.QUserTravelGroupEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class TravelGroupRepositoryImpl implements TravelGroupRepositoryFragment {

    private final JPAQueryFactory jpaQueryFactory;

    public TravelGroupRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<UserGroupsDto> getUserGroupsByUserId(Long userId) {
        QUserEntity user = QUserEntity.userEntity;
        QUserTravelGroupEntity userTravelGroup = QUserTravelGroupEntity.userTravelGroupEntity;
        QTravelGroupEntity group = QTravelGroupEntity.travelGroupEntity;

        return jpaQueryFactory.select(
                Projections.constructor(UserGroupsDto.class, group.id, group.name))
                .from(user)
                .join(userTravelGroup).on(user.id.eq(userTravelGroup.id))
                .join(group).on(userTravelGroup.id.eq(group.id))
                .where(userTravelGroup.id.eq(userId))
                .fetch();

    }

    @Override
    public List<GroupDetailsMembers> getGroupDetailsMembers(Long groupId) {
        QUserEntity user = QUserEntity.userEntity;
        QUserTravelGroupEntity userTravelGroup = QUserTravelGroupEntity.userTravelGroupEntity;
        QTravelGroupEntity group = QTravelGroupEntity.travelGroupEntity;
        return jpaQueryFactory.select(
                Projections.constructor(GroupDetailsMembers.class, user.id, user.name, user.username))
                .from(user)
                .join(userTravelGroup).on(user.id.eq(userTravelGroup.id))
                .join(group).on(userTravelGroup.id.eq(group.id))
                .where(group.id.eq(groupId))
                .fetch();

    }

    @Override
    public List<UserDto> getGroupMembers(Long groupId) {
        QUserEntity user = QUserEntity.userEntity;
        QUserTravelGroupEntity userTravelGroup = QUserTravelGroupEntity.userTravelGroupEntity;
        return jpaQueryFactory.select(Projections.constructor(UserDto.class,
                user.id,
                user.name,
                user.username,
                user.email))
                .from(user).join(userTravelGroup).on(userTravelGroup.travelGroup.id.eq(groupId))
                .where(userTravelGroup.travelGroup.id.eq(groupId))
                .fetch();

    }

    @Override
    public List<UserParticipationDto> getUsersWithParticipation(Long groupId) {
        QUserEntity user = QUserEntity.userEntity;
        QUserTravelGroupEntity userTravelGroup = QUserTravelGroupEntity.userTravelGroupEntity;

        return jpaQueryFactory.select(Projections.constructor(UserParticipationDto.class,
                userTravelGroup.id,
                user.username,
                user.email,
                userTravelGroup.expensesParticipation
                ))
                .from(user).join(userTravelGroup).on(userTravelGroup.user.id.eq(user.id))
                .where(userTravelGroup.travelGroup.id.eq(groupId))
                .distinct()
                .fetch();

    }
}
