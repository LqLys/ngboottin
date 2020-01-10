package com.example.tinbackend.security.domain.expense.repository;

import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findAllByUserTravelGroup_TravelGroup_Id(Long groupId);
}
