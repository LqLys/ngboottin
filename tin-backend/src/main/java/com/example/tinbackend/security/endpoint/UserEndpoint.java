package com.example.tinbackend.security.endpoint;

import com.example.tinbackend.security.domain.user.dto.AddUserDto;
import com.example.tinbackend.security.domain.user.dto.ChangePasswordDto;
import com.example.tinbackend.security.domain.user.dto.EditUserDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserEndpoint {

    private final UserService userService;


    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }

    @PutMapping("")
    public UserDto editUser(@Valid @RequestBody EditUserDto editUserDto){
        return userService.editUser(editUserDto);
    }

    @PostMapping("")
    public UserDto addUser(@Valid @RequestBody AddUserDto addUserDto){
        return userService.saveUser(addUserDto);
    }

    @PutMapping("/change-password")
    public void changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(changePasswordDto);
    }



}
