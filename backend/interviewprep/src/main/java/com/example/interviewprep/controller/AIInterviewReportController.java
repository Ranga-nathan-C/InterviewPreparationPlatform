package com.example.interviewprep.controller;


import com.example.interviewprep.dto.AIInterviewReportResponse;
import com.example.interviewprep.service.AIInterviewReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/ai-interview")
public class AIInterviewReportController {

    private final AIInterviewReportService aiInterviewReportService;

    public AIInterviewReportController(AIInterviewReportService aiInterviewReportService){
        this.aiInterviewReportService=aiInterviewReportService;
    }


    @GetMapping("/{sessionId}/report")
    public AIInterviewReportResponse getReport(@PathVariable Long sessionId){
        return aiInterviewReportService.getReport(sessionId);
    }
}
