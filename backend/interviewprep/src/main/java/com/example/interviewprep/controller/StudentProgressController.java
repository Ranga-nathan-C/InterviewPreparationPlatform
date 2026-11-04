package com.example.interviewprep.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.interviewprep.service.StudentProgressService;
import com.example.interviewprep.entity.StudentProgress;

import org.springframework.security.core.Authentication;
import com.example.interviewprep.dto.ProgressSummaryResponse;
import java.util.List;

@RestController
@RequestMapping("/students/progress")
public class StudentProgressController {
    

    private final StudentProgressService studentProgressService;
    
    public StudentProgressController(StudentProgressService studentProgressService){
        this.studentProgressService = studentProgressService;
    }
    @GetMapping
    public List<StudentProgress> getAllStudentProgress() {
        return studentProgressService.getAllStudentProgress();
    }
    @GetMapping("/summary")
    public ProgressSummaryResponse getProgressSummary(
            Authentication authentication) {

        String studentEmail = authentication.getName();
                
        return studentProgressService.getProgressSummary(studentEmail);
    }

}
