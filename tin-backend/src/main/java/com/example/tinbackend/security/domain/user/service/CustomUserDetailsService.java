package com.example.tinbackend.security.domain.user.service;

import com.example.tinbackend.security.domain.role.entity.RoleEntity;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findByUsername(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
    }

    private User getUserDetails(UserEntity userEntity) {
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                getGrantedAuthorities(userEntity));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserEntity userEntity) {
        final String authorities = userEntity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.joining(","));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
    }
}
