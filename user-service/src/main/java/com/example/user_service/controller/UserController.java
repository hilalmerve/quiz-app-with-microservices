package com.example.user_service.controller;

import com.example.user_service.dto.RegisterUserRequest;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("create")
    public ResponseEntity<Long> createUser(@RequestBody RegisterUserRequest registerUserRequest) {
        return new ResponseEntity<>(userService.createUser(registerUserRequest), HttpStatus.CREATED);
    }
}
