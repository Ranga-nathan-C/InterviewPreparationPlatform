package com.example.interviewprep.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewprep.service.GeminiTestService;

@RestController
public class GeminiTestController {

    private final GeminiTestService geminiTestService;

    public GeminiTestController(GeminiTestService geminiTestService) {
        this.geminiTestService = geminiTestService;
    }

    @GetMapping("/test-gemini")
    public String testGemini() {
        return geminiTestService.testGemini();
    }
}