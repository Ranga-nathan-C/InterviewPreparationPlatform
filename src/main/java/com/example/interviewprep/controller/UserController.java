package com.example.interviewprep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewprep.dto.LoginRequest;
import com.example.interviewprep.dto.UserRegistrationRequest;
import com.example.interviewprep.service.UserService;
import com.example.interviewprep.service.JwtService;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService){
        this.userService=userService;
        this.jwtService=jwtService;
    }
    @PostMapping("/register")
    public String registerUser(@RequestBody UserRegistrationRequest request) {

        userService.registerUser(request);

        return "User Registered Successfully";
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/extract")
    public String extractEmail(@RequestHeader("Authorization") String authHeader) {
        System.out.println("Reached Controller");
        System.out.println(authHeader);
        String token = authHeader.substring(7);

        return jwtService.extractEmail(token);
    }

    @GetMapping("/user/profile")
    public String userProfile() {
        return "Welcome User";
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Welcome Admin";
    }
}