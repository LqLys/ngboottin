package com.example.tinbackend.security.domain.expense.service;

import com.example.tinbackend.security.domain.expense.dto.AddExpenseDto;
import com.example.tinbackend.security.domain.expense.dto.EditExpenseDto;
import com.example.tinbackend.security.domain.expense.dto.ExpenseDto;
import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import com.example.tinbackend.security.domain.expense.mapper.ExpenseMapper;
import com.example.tinbackend.security.domain.expense.repository.ExpenseRepository;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import com.example.tinbackend.security.domain.user.repository.UserRepository;
import com.example.tinbackend.security.domain.usertravelgroup.dto.UserGroupSettledBalanceDto;
import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import com.example.tinbackend.security.domain.usertravelgroup.repository.UserTravelGroupRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;
    private final UserTravelGroupRepository userTravelGroupRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, ExpenseMapper expenseMapper, UserTravelGroupRepository userTravelGroupRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
        this.userTravelGroupRepository = userTravelGroupRepository;
        this.userRepository = userRepository;
    }


    public ExpenseDto findExpenseById(Long id) {
        return expenseMapper.map(expenseRepository.getOne(id), ExpenseDto.class);
    }

    public List<ExpenseDto> findAll() {
        return expenseMapper.mapAsList(expenseRepository.findAll(), ExpenseDto.class);
    }

    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    public ExpenseDto editExpense(EditExpenseDto editExpenseDto) {
        ExpenseEntity expenseEntity = expenseRepository.findById(editExpenseDto.getId())
                .orElseThrow(IllegalArgumentException::new);
        if (editExpenseDto.getTitle() != null) {
            expenseEntity.setTitle(editExpenseDto.getTitle());
        }
        if (editExpenseDto.getAmount() != null) {
            expenseEntity.setAmount(editExpenseDto.getAmount());
        }
        return expenseMapper.map(expenseRepository.save(expenseEntity), ExpenseDto.class);
    }

    public ExpenseDto addExpense(AddExpenseDto addExpenseDto) {
        final UserEntity userEntity = userRepository.findByUsername(addExpenseDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", addExpenseDto.getUsername())));

        UserTravelGroupEntity utg = userTravelGroupRepository
                .findByTravelGroup_IdAndUser_Id(addExpenseDto.getGroupId(),userEntity.getId());

        ExpenseEntity expense = ExpenseEntity.builder()
                .amount(addExpenseDto.getAmount())
                .title(addExpenseDto.getTitle())
                .userTravelGroup(utg)
                .build();
        return expenseMapper.map(expenseRepository.save(expense), ExpenseDto.class);
    }

    public List<ExpenseDto> getExpensesByGroupId(Long groupId) {
        return expenseMapper.mapAsList(expenseRepository.findAllByUserTravelGroup_TravelGroup_Id(groupId), ExpenseDto.class);
    }

    public List<UserGroupSettledBalanceDto> getGroupExpensesByUsername(String username) {
        return userTravelGroupRepository.findUserSettledBalance(username);

    }
}
