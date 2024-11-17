package com.diploma.banking.controller;

import com.diploma.banking.model.dto.UserResponseDto;
import com.diploma.banking.model.dto.input.UserInput;
import com.diploma.banking.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody UserInput user) {
        return ResponseEntity.ok(
                UserResponseDto.from(userService.createUser(user))
        );
    }
}
