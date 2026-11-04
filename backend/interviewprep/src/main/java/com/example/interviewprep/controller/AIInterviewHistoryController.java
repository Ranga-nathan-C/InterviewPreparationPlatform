package com.example.interviewprep.controller;

import com.example.interviewprep.dto.AIInterviewHistoryResponse;
import com.example.interviewprep.service.AIInterviewHistoryService;
import com.example.interviewprep.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students/ai-interview")
public class AIInterviewHistoryController {

    private final AIInterviewHistoryService aiInterviewHistoryService;
    private final UserService userService;

    public AIInterviewHistoryController(
            AIInterviewHistoryService aiInterviewHistoryService,
            UserService userService) {

        this.aiInterviewHistoryService = aiInterviewHistoryService;
        this.userService = userService;
    }

    @GetMapping("/history")
    public List<AIInterviewHistoryResponse> getInterviewHistory(
            Authentication authentication) {

        String email = authentication.getName();

        Long studentId = userService.findByEmail(email).getId();

        return aiInterviewHistoryService.getInterviewHistory(studentId);
    }
}