package com.example.interviewprep.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.interviewprep.dto.InterviewQuestionRequest;
import com.example.interviewprep.entity.InterviewQuestion;
import com.example.interviewprep.service.InterviewQuestionService;


@RestController
@RequestMapping("/admin/questions")
public class InterviewQuestionController {
    


    private final InterviewQuestionService interviewQuestionService;

    public InterviewQuestionController(InterviewQuestionService interviewQuestionService){
        this.interviewQuestionService=interviewQuestionService;
    }

    @PostMapping
    public InterviewQuestion createQuestion(@RequestBody InterviewQuestionRequest request){
        return interviewQuestionService.createQuestion(request);
    }

    @PostMapping("/bulk")
    public List<InterviewQuestion> createQuestions(
            @RequestBody List<InterviewQuestionRequest> requests) {

        return interviewQuestionService.createQuestions(requests);
    }

    @GetMapping
    public Page<InterviewQuestion> getAllQuestions(Pageable pageable){
        return interviewQuestionService.getAllQuestions(pageable);
    }

}
