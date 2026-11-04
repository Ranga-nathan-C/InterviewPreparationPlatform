package com.example.interviewprep.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewprep.dto.InterviewQuestionRequest;
import com.example.interviewprep.entity.InterviewQuestion;
import com.example.interviewprep.service.InterviewQuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/admin/questions")
@SecurityRequirement(name = "bearerAuth")
public class AdminQuestionController{
    private final InterviewQuestionService interviewQuestionService;


    public AdminQuestionController(InterviewQuestionService interviewQuestionService){
        this.interviewQuestionService=interviewQuestionService;
    }

    @GetMapping("/{questionId}")
    public InterviewQuestion getQuestionById(@PathVariable Long questionId){
        return interviewQuestionService.getQuestionById(questionId);
    }

    @PutMapping("/{questionId}")
    public InterviewQuestion updateQuestionById(@PathVariable Long questionId ,@Valid @RequestBody InterviewQuestionRequest request){
        return interviewQuestionService.updateQuestion(questionId, request);
    }

    @DeleteMapping("/{questionId}")
    public void deleteQuestionId(@PathVariable Long questionId){
        interviewQuestionService.deleteQuestionId(questionId);
    }

}