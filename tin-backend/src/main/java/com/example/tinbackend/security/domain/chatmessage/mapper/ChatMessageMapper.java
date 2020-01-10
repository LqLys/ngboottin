package com.example.tinbackend.security.domain.chatmessage.mapper;

import com.example.tinbackend.security.domain.chatmessage.entity.ChatMessageEntity;
import com.example.tinbackend.security.domain.travelgroup.dto.TravelGroupDto;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ChatMessageMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(ChatMessageEntity.class, TravelGroupDto.class)
                .byDefault()
                .register();
    }
}
