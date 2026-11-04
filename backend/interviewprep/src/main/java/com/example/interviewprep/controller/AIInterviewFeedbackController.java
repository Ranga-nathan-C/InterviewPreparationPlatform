package com.example.interviewprep.controller;


import com.example.interviewprep.dto.AIInterviewFeedbackResponse;
import com.example.interviewprep.service.AIInterviewFeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/ai-interview")
public class AIInterviewFeedbackController {
    private final AIInterviewFeedbackService aiInterviewFeedbackService;

    public AIInterviewFeedbackController(AIInterviewFeedbackService aiInterviewFeedbackService){
        this.aiInterviewFeedbackService=aiInterviewFeedbackService;
    }

    @GetMapping("{sessionId}/feedback")
    public AIInterviewFeedbackResponse getPersonalizedFeedback(@PathVariable Long sessionId){
        return aiInterviewFeedbackService.getPersonalizedFeedback(sessionId);
    }
}
