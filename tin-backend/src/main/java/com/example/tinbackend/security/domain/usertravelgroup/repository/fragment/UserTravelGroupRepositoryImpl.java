package com.example.tinbackend.security.domain.usertravelgroup.repository.fragment;

import com.example.tinbackend.security.domain.travelgroup.entity.QTravelGroupEntity;
import com.example.tinbackend.security.domain.user.entity.QUserEntity;
import com.example.tinbackend.security.domain.usertravelgroup.dto.UserGroupSettledBalanceDto;
import com.example.tinbackend.security.domain.usertravelgroup.entity.QUserTravelGroupEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class UserTravelGroupRepositoryImpl implements UserTravelGroupRepositoryFragment {

    private final JPAQueryFactory jpaQueryFactory;

    public UserTravelGroupRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<UserGroupSettledBalanceDto> findUserSettledBalance(String username) {
        QUserEntity user = QUserEntity.userEntity;
        QUserTravelGroupEntity participation = QUserTravelGroupEntity.userTravelGroupEntity;
        QTravelGroupEntity group = QTravelGroupEntity.travelGroupEntity;

        return jpaQueryFactory.select(Projections.constructor(UserGroupSettledBalanceDto.class,
                group.name, group.destination, group.startDate, group.endDate, participation.settledBalance))
                .from(group).join(participation).on(group.id.eq(participation.travelGroup.id))
                .join(user).on(participation.user.id.eq(user.id))
                .where(user.username.eq(username))
                .fetch();
    }
}
