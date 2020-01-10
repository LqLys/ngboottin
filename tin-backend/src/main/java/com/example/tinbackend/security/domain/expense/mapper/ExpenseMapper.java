package com.example.tinbackend.security.domain.expense.mapper;

import com.example.tinbackend.security.domain.expense.dto.ExpenseDto;
import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import com.example.tinbackend.security.domain.travelgroup.dto.TravelGroupDto;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.endpoint.ExpenseEndpoint;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(ExpenseEntity.class, ExpenseDto.class)
                .byDefault()
                .register();
    }
}
