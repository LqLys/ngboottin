package com.example.tinbackend.security.domain.user.mapper;

import com.example.tinbackend.security.domain.travelgroup.dto.TravelGroupDto;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.user.dto.AddUserDto;
import com.example.tinbackend.security.domain.user.dto.EditUserDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(UserEntity.class, UserDto.class)
                .byDefault()
                .register();
        factory.classMap(EditUserDto.class, UserEntity.class)
                .byDefault()
                .register();
        factory.classMap(AddUserDto.class, UserEntity.class)
                .byDefault()
                .register();
    }
}
