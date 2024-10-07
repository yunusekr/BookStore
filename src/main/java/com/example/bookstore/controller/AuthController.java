package com.example.bookstore.controller;

import com.example.bookstore.dto.RegisterUser;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterUser register(@RequestBody User registerUser){
        User createdUser = authenticationService.register(registerUser);
        return new RegisterUser(createdUser.getUsername(), createdUser.getEmail(), createdUser.getPassword());
    }
}