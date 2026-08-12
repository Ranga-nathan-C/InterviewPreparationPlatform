package com.example.interviewprep.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.interviewprep.dto.LoginRequest;
import com.example.interviewprep.dto.UserRegistrationRequest;
// import com.example.interviewprep.entity.Role;
import com.example.interviewprep.entity.User;
import com.example.interviewprep.repository.UserRepository;



@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.passwordEncoder=passwordEncoder;
        this.userRepository=userRepository;
        this.jwtService=jwtService;
    }

    public void registerUser(UserRegistrationRequest request) throws IllegalArgumentException {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if(existingUser.isPresent()){
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        System.out.println("User Role: " + user.getRole());
        userRepository.save(user);
    }
    public String login(LoginRequest request){
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
                return jwtService.generateToken(user.getEmail());
            }else{
                return "Invalid password";
            }
        }else{
            return "User not found";
        }
    }
}