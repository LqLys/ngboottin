package com.example.tinbackend.security.endpoint;

import com.example.tinbackend.security.domain.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/validate")
public class ValidationEndpoint {

    private final UserService userService;

    public ValidationEndpoint(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/username-exists")
    public Boolean usernameAlreadyTaken(@RequestParam String username){
        return userService.findByUsername(username).isPresent();
    }
}
