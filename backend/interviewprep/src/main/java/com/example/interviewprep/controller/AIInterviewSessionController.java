package com.example.interviewprep.controller;


import com.example.interviewprep.dto.AIInterviewAnswerRequest;
import com.example.interviewprep.dto.AIInterviewAnswerResponse;
import com.example.interviewprep.dto.AIInterviewStartResponse;
import com.example.interviewprep.entity.AIDifficulty;
import com.example.interviewprep.entity.AIInterviewAnswer;
import com.example.interviewprep.repository.UserRepository;
import com.example.interviewprep.service.AIInterviewAnswerService;
import com.example.interviewprep.service.AIInterviewSessionService;
import com.example.interviewprep.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students/ai-interview")
public class AIInterviewSessionController {

    private final AIInterviewSessionService aiInterviewSessionService;
    private final UserRepository userRepository;
    private final AIInterviewAnswerService aiInterviewAnswerService;

    public AIInterviewSessionController(
            AIInterviewSessionService aiInterviewSessionService,
            UserRepository userRepository,
            AIInterviewAnswerService aiInterviewAnswerService){
        this.aiInterviewSessionService=aiInterviewSessionService;
        this.userRepository=userRepository;
        this.aiInterviewAnswerService=aiInterviewAnswerService;
    }

    @PostMapping("/start")
    public AIInterviewStartResponse startInterview(Authentication authentication, @RequestParam String category , @RequestParam AIDifficulty difficulty){
        String email = authentication.getName();

        User student = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("User not found"));

        return aiInterviewSessionService.startInterview(student,category,difficulty);

    }

    @PostMapping("/{sessionId}/answer")
    public AIInterviewAnswerResponse submitAnswer(
            @PathVariable Long sessionId,
            @RequestBody AIInterviewAnswerRequest aiInterviewAnswerRequest,
            Authentication authentication){
        String email = authentication.getName();
        return aiInterviewAnswerService.submitAnswer(sessionId,email,aiInterviewAnswerRequest);
    }
}
