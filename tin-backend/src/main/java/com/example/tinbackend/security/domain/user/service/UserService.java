package com.example.tinbackend.security.domain.user.service;

import com.example.tinbackend.security.domain.role.entity.RoleEntity;
import com.example.tinbackend.security.domain.role.repository.RoleRepository;
import com.example.tinbackend.security.domain.user.dto.AddUserDto;
import com.example.tinbackend.security.domain.user.dto.ChangePasswordDto;
import com.example.tinbackend.security.domain.user.dto.EditUserDto;
import com.example.tinbackend.security.domain.user.dto.UserDto;
import com.example.tinbackend.security.domain.user.entity.UserEntity;
import com.example.tinbackend.security.domain.user.mapper.UserMapper;
import com.example.tinbackend.security.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public UserEntity findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserDto saveUser(AddUserDto addUserDto) {
        RoleEntity userRole = roleRepository.findByName("USER");
        UserEntity user = UserEntity.builder()
                .active(true)
                .email(addUserDto.getEmail())
                .name(addUserDto.getName())
                .username(addUserDto.getUsername())
                .password(encoder.encode(addUserDto.getPassword()))
                .roles(new ArrayList<>(Arrays.asList(userRole)))
                .build();
        return userMapper.map(userRepository.save(user), UserDto.class);
    }

    public UserDto findUserById(Long id) {
        return userMapper.map(userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("user with id " + id + "not found")), UserDto.class);

    }

    public List<UserDto> findAll() {
        return userMapper.mapAsList(userRepository.findAll(), UserDto.class);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto editUser(EditUserDto editUserDto) {
        UserEntity userEntity = userRepository.findById(editUserDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("user with id " + editUserDto.getId() + "not found"));
        if (editUserDto.getName() != null) {
            userEntity.setName(editUserDto.getName());
        }
        if (editUserDto.getUsername() != null) {
            userEntity.setUsername(editUserDto.getUsername());
        }
        if (editUserDto.getEmail() != null) {
            userEntity.setEmail(editUserDto.getEmail());
        }
        if(editUserDto.getPassword() != null && editUserDto.getPassword().length() > 0) {
            userEntity.setPassword(encoder.encode(editUserDto.getPassword()));
        }
        return userMapper.map(userRepository.save(userEntity), UserDto.class);

    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void changePassword(ChangePasswordDto changePasswordDto) {
        final UserEntity user = userRepository.findByUsername(changePasswordDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("user with id " + changePasswordDto.getUsername() + "not found"));

        if(encoder.matches(changePasswordDto.getOldPassword(),user.getPassword())) {
            user.setPassword(encoder.encode(changePasswordDto.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new RuntimeException("incorrect password");
        }


    }

//    public UserDto addUser(AddUserDto addUserDto) {
//        RoleEntity userRole = roleRepository.findByName("USER");
//
//        UserEntity user = UserEntity.builder()
//                .active(true)
//                .email(addUserDto.getEmail())
//                .name(addUserDto.getName())
//                .username(addUserDto.getUsername())
//                .password(addUserDto.getPassword())
//                .roles(new ArrayList<>(Arrays.asList(userRole)))
//                .build();
//        return userMapper.map(userRepository.save(user), UserDto.class);
//    }

}
