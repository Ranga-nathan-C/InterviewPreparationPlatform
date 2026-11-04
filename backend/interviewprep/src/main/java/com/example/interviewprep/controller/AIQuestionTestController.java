package com.example.interviewprep.controller;


import com.example.interviewprep.dto.AIQuestionResponse;
import com.example.interviewprep.entity.AIDifficulty;
import com.example.interviewprep.service.AIQuestionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aiquestions")
public class AIQuestionTestController {
    private final AIQuestionService aiQuestionService;

    public AIQuestionTestController(AIQuestionService aiQuestionService){
        this.aiQuestionService=aiQuestionService;
    }


    @GetMapping("/test-ai-question")
    public AIQuestionResponse testQuestion(@RequestParam String category, @RequestParam AIDifficulty difficulty){
        System.out.println("🔥 TEST AI QUESTION CONTROLLER REACHED");
        return aiQuestionService.generateQuestion(category,difficulty);
    }

}
