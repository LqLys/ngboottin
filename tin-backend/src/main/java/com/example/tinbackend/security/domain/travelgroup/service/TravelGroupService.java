package com.example.tinbackend.security.domain.travelgroup.service;

import com.example.tinbackend.security.domain.expense.entity.ExpenseEntity;
import com.example.tinbackend.security.domain.travelgroup.dto.*;
import com.example.tinbackend.security.domain.travelgroup.entity.GroupStatus;
import com.example.tinbackend.security.domain.travelgroup.entity.TravelGroupEntity;
import com.example.tinbackend.security.domain.travelgroup.mapper.TravelGroupMapper;
import com.example.tinbackend.security.domain.travelgroup.repository.TravelGroupRepository;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.dto.UserParticipationDto;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import com.example.tinbackend.security.domain.user.repository.UserRepository;
import com.example.tinbackend.security.domain.usertravelgroup.dto.RemoveParticipatonDto;
import com.example.tinbackend.security.domain.usertravelgroup.dto.UpdateParticipationDto;
import com.example.tinbackend.security.domain.usertravelgroup.entity.UserTravelGroupEntity;
import com.example.tinbackend.security.domain.usertravelgroup.repository.UserTravelGroupRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TravelGroupService {

    private final TravelGroupRepository travelGroupRepository;
    private final TravelGroupMapper travelGroupMapper;
    private final UserRepository userRepository;
    private final UserTravelGroupRepository userTravelGroupRepository;

    public TravelGroupService(TravelGroupRepository travelGroupRepository, TravelGroupMapper travelGroupMapper, UserRepository userRepository, UserTravelGroupRepository userTravelGroupRepository) {
        this.travelGroupRepository = travelGroupRepository;
        this.travelGroupMapper = travelGroupMapper;
        this.userRepository = userRepository;
        this.userTravelGroupRepository = userTravelGroupRepository;
    }


    public TravelGroupDto findById(Long id) {

        return travelGroupMapper.map(travelGroupRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("user with id" + id + "not found")),
                TravelGroupDto.class);
    }

    public List<TravelGroupDto> findAll() {
        return travelGroupMapper.mapAsList(travelGroupRepository.findAll(), TravelGroupDto.class);
    }

    public void deleteById(Long id) {
        travelGroupRepository.deleteById(id);
    }

    public TravelGroupDto editGroup(EditGroupDto editGroupDto) {
        TravelGroupEntity travelGroupEntity = travelGroupRepository.findById(editGroupDto.getId())
                .orElseThrow(IllegalArgumentException::new);
        if (editGroupDto.getName() != null) {
            travelGroupEntity.setName(editGroupDto.getName());
        }
        if (editGroupDto.getDestination() != null) {
            travelGroupEntity.setDestination(editGroupDto.getDestination());
        }
        if (editGroupDto.getStartDate() != null) {
            travelGroupEntity.setStartDate(editGroupDto.getStartDate());
        }
        if (editGroupDto.getEndDate() != null) {
            travelGroupEntity.setEndDate(editGroupDto.getEndDate());
        }
        return travelGroupMapper.map(travelGroupRepository.save(travelGroupEntity), TravelGroupDto.class);
    }

    public TravelGroupDto addGroup(AddGroupDto addGroupDto) {

        TravelGroupEntity group = TravelGroupEntity.builder()
                .groupStatus(GroupStatus.CREATED)
                .name(addGroupDto.getName())
                .destination(addGroupDto.getDestination())
                .startDate(addGroupDto.getStartDate())
                .endDate(addGroupDto.getEndDate())
                .build();
        return travelGroupMapper.map(travelGroupRepository.save(group), TravelGroupDto.class);
    }

    public void addGroupWithUsers(AddGroupWithUsersDto addGroup) {
        List<Long> userIds = addGroup.getUserIds();
        List<UserEntity> invitedUsers = userRepository.findAllByIdIn(userIds);

        TravelGroupEntity group = TravelGroupEntity.builder()
                .groupStatus(GroupStatus.CREATED)
                .name(addGroup.getName())
                .destination(addGroup.getDestination())
                .startDate(addGroup.getStartDate())
                .endDate(addGroup.getEndDate())
                .build();
        TravelGroupEntity travelGroup = travelGroupRepository.save(group);
        invitedUsers.forEach(user -> {
            UserTravelGroupEntity userInTravel = UserTravelGroupEntity.builder()
                    .user(user)
                    .travelGroup(travelGroup)
                    .build();
            userTravelGroupRepository.save(userInTravel);
        });


    }

    public void joinGroup(JoinGroupDto joinGroupDto) {
        UserEntity user = userRepository.findByUsername(joinGroupDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", joinGroupDto.getUsername())));
        TravelGroupEntity travelGroup = travelGroupRepository.getOne(joinGroupDto.getGroupId());
        UserTravelGroupEntity userInGroup = UserTravelGroupEntity.builder()
                .user(user)
                .travelGroup(travelGroup)
                .expenses(new ArrayList<>())
                .build();
        userTravelGroupRepository.save(userInGroup);
    }

    public List<UserDto> getGroupMembers(Long groupId) {
        return travelGroupRepository.getGroupMembers(groupId);
    }

    public List<UserParticipationDto> getUsersWithParticipation(Long groupId) {
        return travelGroupRepository.getUsersWithParticipation(groupId);
    }

    public void updateParticipation(UpdateParticipationDto updateParticipationDto) {
        final UserTravelGroupEntity utg = userTravelGroupRepository.getOne(updateParticipationDto.getId());
        utg.setExpensesParticipation(updateParticipationDto.getParticipation());
        userTravelGroupRepository.save(utg);
    }

    public boolean isMemberAlready(Long groupId, String username) {
        final UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", username)));
        return userTravelGroupRepository.findByTravelGroup_IdAndUser_Id(groupId, userEntity.getId()) != null;
    }

    public void removeParticipation(RemoveParticipatonDto removeParticipatonDto) {
        userTravelGroupRepository.deleteById(removeParticipatonDto.getId());
    }

    @Scheduled(cron = "@midnight")
    public void settleGroups() {
        final LocalDate currentDate = LocalDate.now();
        final List<TravelGroupEntity> allByEndDate = travelGroupRepository.findAllByEndDate(currentDate);
        settleGroups(allByEndDate);


    }

    private void settleGroups(List<TravelGroupEntity> allByEndDate) {
        final List<Long> groupIds = allByEndDate.stream().map(TravelGroupEntity::getId).collect(Collectors.toList());
        final List<UserTravelGroupEntity> allByTravelGroup_idIn = userTravelGroupRepository.findAllByTravelGroup_IdIn(groupIds);
        final Map<Long, List<UserTravelGroupEntity>> aggregatedParticipation = allByTravelGroup_idIn.stream()
                .collect(Collectors.groupingBy(u -> u.getTravelGroup().getId()));


        aggregatedParticipation.forEach((groupId, groupMembers) -> {
            final Map<Long, BigDecimal> userExpenses = groupMembers.stream()
                    .collect(Collectors.toMap(u -> u.getUser().getId(),
                            member -> member.getExpenses().stream()
                                    .map(ExpenseEntity::getAmount)
                                    .reduce(BigDecimal::add)
                                    .orElse(BigDecimal.ZERO)));
            BigDecimal totalExpenses = userExpenses.values().stream().reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
            groupMembers.forEach(m-> {
                BigDecimal shouldPay = m.getExpensesParticipation().multiply(totalExpenses)
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
                m.setSettledBalance(userExpenses.get(m.getUser().getId()).subtract(shouldPay));
                System.out.println(m.getSettledBalance());
                userTravelGroupRepository.save(m);
            });

        });
    }

    public void settleGroupById(Long groupId) {
        TravelGroupEntity travelGroupEntity = travelGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException(String.format("group with id %s not found", groupId)));
        settleGroups(new ArrayList<>(Collections.singletonList(travelGroupEntity)));

    }
}
