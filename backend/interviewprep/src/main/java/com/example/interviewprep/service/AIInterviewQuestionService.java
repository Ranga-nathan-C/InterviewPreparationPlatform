package com.example.interviewprep.service;

import com.example.interviewprep.dto.AIQuestionResponse;
import com.example.interviewprep.entity.AIInterviewQuestion;
import com.example.interviewprep.entity.AIInterviewSession;
import com.example.interviewprep.repository.AIInterviewQuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class AIInterviewQuestionService {

    private final AIInterviewQuestionRepository aiInterviewQuestionRepository;
    private final AIQuestionService aiQuestionService;

    public AIInterviewQuestionService(AIInterviewQuestionRepository aiInterviewQuestionRepository,AIQuestionService aiQuestionService){
        this.aiInterviewQuestionRepository=aiInterviewQuestionRepository;
        this.aiQuestionService=aiQuestionService;
    }

    public AIInterviewQuestion saveQuestion(AIInterviewSession session, AIQuestionResponse aiQuestionResponse){
        AIInterviewQuestion question = new AIInterviewQuestion();
        question.setSession(session);
        question.setQuestion(aiQuestionResponse.getQuestion());
        question.setExpectedAnswer(aiQuestionResponse.getExpectedAnswer());
        question.setDifficulty(aiQuestionResponse.getDifficulty());
        question.setQuestionType(aiQuestionResponse.getQuestionType());

        return aiInterviewQuestionRepository.save(question);
    }

    public AIInterviewQuestion generateAndSaveQuestion(AIInterviewSession aiInterviewSession){
        AIQuestionResponse response = aiQuestionService.generateQuestion(aiInterviewSession.getCategory(),aiInterviewSession.getDifficulty());
        return  saveQuestion(aiInterviewSession,response);
    }
}
