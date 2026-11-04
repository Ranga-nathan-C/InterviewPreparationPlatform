package com.example.interviewprep.controller;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewprep.dto.InterviewSessionResponse;
import com.example.interviewprep.dto.SubmitAnswerRequest;
import com.example.interviewprep.service.InterviewSessionService;
import com.example.interviewprep.dto.InterviewHistoryResponse;
import com.example.interviewprep.repository.UserRepository;
import com.example.interviewprep.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


@RestController
@RequestMapping("/students/interview")
public class InterviewSessionController {

    private final InterviewSessionService interviewSessionService;
    private final UserRepository userRepository;

    public InterviewSessionController(
            InterviewSessionService interviewSessionService,
            UserRepository userRepository) {
        this.interviewSessionService = interviewSessionService;
        this.userRepository=userRepository;
    }

    @PostMapping("/start")
    public InterviewSessionResponse startInterview(
            Authentication authentication,
            @RequestParam String category,
            @RequestParam String difficultyLevel) {

        String email = authentication.getName();

        return interviewSessionService.startInterview(
                email,
                category,
                difficultyLevel
        );
    }

    @PostMapping("/{sessionId}/answer")
    public void submitAnswer(
            @PathVariable Long sessionId,
            @RequestBody SubmitAnswerRequest request) {

        interviewSessionService.submitAnswer(sessionId, request);
    }

    @PostMapping("/{sessionId}/complete")
    public InterviewSessionResponse completeInterview(@PathVariable Long sessionId){
        return interviewSessionService.completeInterview(sessionId);
    }

    @GetMapping("/history")
    public Page<InterviewHistoryResponse> getInterviewHistory(Authentication authentication,
                                                @PageableDefault(size = 5, sort = "startedAt") Pageable pageable){
        String email = authentication.getName();

        User user = userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException("Email not found"));

        return interviewSessionService.getInterviewHistory(user.getId(),pageable);
    }
}