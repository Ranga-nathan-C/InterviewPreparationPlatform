package com.example.interviewprep.controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.interviewprep.dto.AnswerSubmissionRequest;
import com.example.interviewprep.dto.InterviewQuestionResponse;
import com.example.interviewprep.entity.InterviewQuestion;
import com.example.interviewprep.service.InterviewQuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/students/questions")
@SecurityRequirement(name = "bearerAuth")
public class StudentQuestionController {
    

    private final InterviewQuestionService interviewQuestionService;

    public StudentQuestionController(InterviewQuestionService interviewQuestionService){
        this.interviewQuestionService=interviewQuestionService;
    }

    @GetMapping
    public List<InterviewQuestionResponse> getAllQuestions(){
        return interviewQuestionService.findAllForStudents();
    }

    @GetMapping("page")
    public Page<InterviewQuestionResponse> getQuestionWithPagination(@PageableDefault(size=5) @ParameterObject Pageable pageable){
        Page<InterviewQuestion> questions = interviewQuestionService.getAllQuestions(pageable);

        return questions.map(question-> new InterviewQuestionResponse(
            question.getId(),
            question.getQuestion(),
            question.getOptionA(),
            question.getOptionB(),
            question.getOptionC(),
            question.getOptionD(),
            question.getCategory(),
            question.getTopic(),
            question.getDifficultyLevel()
        ));
    } 


    @PostMapping("/{questionId}/answer")
    public boolean submitAnswer(@PathVariable Long questionId, @RequestBody AnswerSubmissionRequest request){

        String studentEmail = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();
        return interviewQuestionService.checkAnswer(questionId, request.getAnswer(),studentEmail);
    }

    @GetMapping("/unanswered")
    public List<InterviewQuestionResponse> getUnAnsweredQuestionsForStudent(String studentEmail){
        String answeredStudentEmail = SecurityContextHolder.getContext()
                                                            .getAuthentication()
                                                            .getName();

        List<InterviewQuestion> questions = interviewQuestionService.getUnansweredQuestions(answeredStudentEmail);
        return questions.stream()
                .map(question-> new InterviewQuestionResponse(
                    question.getId(),
                    question.getQuestion(),
                    question.getOptionA(),
                    question.getOptionB(),
                    question.getOptionC(),
                    question.getOptionD(),
                    question.getCategory(),
                    question.getTopic(),
                    question.getDifficultyLevel()
                )).toList();
    }


    @GetMapping("/category/{category}")
    public List<InterviewQuestionResponse> getQuestionByCategory(@PathVariable String category){
        List<InterviewQuestion> questions = interviewQuestionService.getQuestionByCategory(category);
        return questions.stream()
                        .map(question -> new InterviewQuestionResponse(
                            question.getId(),
                            question.getQuestion(),
                            question.getOptionA(),
                            question.getOptionB(),
                            question.getOptionC(),
                            question.getOptionD(),
                            question.getCategory(),
                            question.getTopic(),
                            question.getDifficultyLevel()
                        )).toList();
    }


    @GetMapping("/difficultyLevel/{difficultyLevel}")
    public List<InterviewQuestionResponse> getQuestionByDifficultyLevel(@PathVariable String difficultyLevel){
        List<InterviewQuestion> questions = interviewQuestionService.getQuestionByDifficultyLevel(difficultyLevel);

        return questions.stream()
                        .map(question-> new InterviewQuestionResponse(
                            question.getId(),
                            question.getQuestion(),
                            question.getOptionA(),
                            question.getOptionB(),
                            question.getOptionC(),
                            question.getOptionD(),
                            question.getCategory(),
                            question.getTopic(),
                            question.getDifficultyLevel()

                        )).toList();
    }

    @GetMapping("/filter")
    public List<InterviewQuestionResponse> getQuestionByCategoryAndDifficultyLevel(@RequestParam String category,@RequestParam String difficultyLevel){

        List<InterviewQuestion> questions = interviewQuestionService.getQuestionByCategoryAndDifficultyLevel(category,difficultyLevel);

        return questions.stream()
                        .map(question-> new InterviewQuestionResponse(
                            question.getId(),
                            question.getQuestion(),
                            question.getOptionA(),
                            question.getOptionB(),
                            question.getOptionC(),
                            question.getOptionD(),
                            question.getCategory(),
                            question.getTopic(),
                            question.getDifficultyLevel()
                        )).toList();
    }


    @GetMapping("/search")
    public List<InterviewQuestionResponse> searchQuestions(@RequestParam String keyword){
        List<InterviewQuestion> questions = interviewQuestionService.searchQuestions(keyword);

        return questions.stream()
                        .map(question-> new InterviewQuestionResponse(
                            question.getId(),
                            question.getQuestion(),
                            question.getOptionA(),
                            question.getOptionB(),
                            question.getOptionC(),
                            question.getOptionD(),
                            question.getCategory(),
                            question.getTopic(),
                            question.getDifficultyLevel()
                        )).toList();
    }


}
