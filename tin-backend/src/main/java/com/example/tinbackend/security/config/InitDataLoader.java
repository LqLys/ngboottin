package com.example.tinbackend.security.config;

import com.example.tinbackend.security.domain.role.entity.RoleEntity;
import com.example.tinbackend.security.domain.role.repository.RoleRepository;
import com.example.tinbackend.security.domain.travelgroup.dto.AddGroupWithUsersDto;
import com.example.tinbackend.security.domain.travelgroup.service.TravelGroupService;
import com.example.tinbackend.security.domain.user.dto.AddUserDto;
import com.example.tinbackend.security.domain.user.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class InitDataLoader {

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final TravelGroupService travelGroupService;

    public InitDataLoader(RoleRepository roleRepository, UserService userService, TravelGroupService travelGroupService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
        this.travelGroupService = travelGroupService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){

        RoleEntity admin = RoleEntity.builder()
                .name("ADMIN")
                .description("ADMIN")
                .build();
        RoleEntity user = RoleEntity.builder()
                .name("USER")
                .description("USER")
                .build();

        List<RoleEntity> roles = new ArrayList<>(Arrays.asList(admin, user));

        roleRepository.saveAll(roles);

        AddUserDto jacek = new AddUserDto("jacek","jacek", "jacek@wp.pl", "123Qwert|");
        AddUserDto janek = new AddUserDto("janek","janek", "janek@wp.pl", "123Qwert|");
        userService.saveUser(jacek);
        userService.saveUser(janek);

        AddGroupWithUsersDto addGroup = AddGroupWithUsersDto.builder()
                .name("warszawa")
                .destination("warszawa")
                .startDate(LocalDate.parse("2019-01-01"))
                .endDate(LocalDate.parse("2019-01-01"))
                .userIds(new ArrayList<>())
                .build();
        travelGroupService.addGroupWithUsers(addGroup);
    }

}
