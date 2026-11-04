package com.example.interviewprep.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewprep.dto.AdminResponse;
import com.example.interviewprep.dto.LoginRequest;
import com.example.interviewprep.dto.UserRegistrationRequest;
import com.example.interviewprep.dto.UserResponse;
import com.example.interviewprep.entity.User;
import com.example.interviewprep.service.JwtService;
import com.example.interviewprep.service.UserService;
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

    @GetMapping("/students/profile")
    public UserResponse studentsProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }

    @GetMapping("/admin/dashboard")
    public AdminResponse adminDashboard(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        return new AdminResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );
    }
}