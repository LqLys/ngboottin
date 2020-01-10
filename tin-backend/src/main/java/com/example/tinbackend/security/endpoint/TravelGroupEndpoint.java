package com.example.tinbackend.security.endpoint;

import com.example.tinbackend.security.domain.travelgroup.dto.*;
import com.example.tinbackend.security.domain.travelgroup.service.TravelGroupService;
import com.example.tinbackend.security.domain.user.dto.AddUserDto;
import com.example.tinbackend.security.domain.user.dto.EditUserDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.dto.UserParticipationDto;
import com.example.tinbackend.security.domain.usertravelgroup.dto.RemoveParticipatonDto;
import com.example.tinbackend.security.domain.usertravelgroup.dto.UpdateParticipationDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/travel-group")
public class TravelGroupEndpoint {

    private final TravelGroupService travelGroupService;

    public TravelGroupEndpoint(TravelGroupService travelGroupService) {
        this.travelGroupService = travelGroupService;
    }


    @GetMapping("/{id}")
    public TravelGroupDto getGroupById(@PathVariable("id") Long id) {
        return travelGroupService.findById(id);
    }

    @GetMapping("")
    public List<TravelGroupDto> getAllGroups() {
        return travelGroupService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable("id") Long id) {
        travelGroupService.deleteById(id);
    }

    @PutMapping("")
    public TravelGroupDto editGroup(@Valid @RequestBody EditGroupDto editGroupDto){
        return travelGroupService.editGroup(editGroupDto);
    }

    @PostMapping("")
    public TravelGroupDto addGroup(@Valid @RequestBody AddGroupDto addGroupDto){
        return travelGroupService.addGroup(addGroupDto);
    }

    @PostMapping("/add-with-users")
    public void addGroupWithUsers(@Valid @RequestBody AddGroupWithUsersDto addGroup){
        travelGroupService.addGroupWithUsers(addGroup);
    }

    @PostMapping("/join-group")
    public void joinGroup(@Valid @RequestBody JoinGroupDto joinGroupDto){
        travelGroupService.joinGroup(joinGroupDto);
    }

    @GetMapping("/users/{groupId}")
    public List<UserDto> getUsersByGroupId(@PathVariable("groupId") Long groupId){
        return travelGroupService.getGroupMembers(groupId);
    }

    @GetMapping("/users-participation/{groupId}")
    public List<UserParticipationDto> getUsersWithParticipation(@PathVariable("groupId") Long groupId) {
        return travelGroupService.getUsersWithParticipation(groupId);
    }

    @PutMapping("/participation")
    public void setTravelGroupService(@Valid @RequestBody UpdateParticipationDto updateParticipationDto){
        travelGroupService.updateParticipation(updateParticipationDto);
    }

    @GetMapping("/{groupId}/member/{username}")
    public boolean isMemberAlready(@PathVariable("groupId") Long groupId, @PathVariable("username") String username){
        return travelGroupService.isMemberAlready(groupId, username);
    }

    @PutMapping("/remove-member/")
    public void removeMember(@Valid @RequestBody RemoveParticipatonDto removeParticipatonDto){
        travelGroupService.removeParticipation(removeParticipatonDto);
    }


    @PostMapping("/settle")
    public void settleGroups(){
        travelGroupService.settleGroups();
    }

    @PostMapping("/settle/{groupId}")
    public void settleGroupById(@PathVariable("groupId") Long groupId){
        travelGroupService.settleGroupById(groupId);
    }



}
