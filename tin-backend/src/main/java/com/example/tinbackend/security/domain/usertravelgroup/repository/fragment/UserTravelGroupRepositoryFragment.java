package com.example.tinbackend.security.domain.usertravelgroup.repository.fragment;

import com.example.tinbackend.security.domain.usertravelgroup.dto.UserGroupSettledBalanceDto;

import java.util.List;

public interface UserTravelGroupRepositoryFragment {

    List<UserGroupSettledBalanceDto> findUserSettledBalance(String username);


}
