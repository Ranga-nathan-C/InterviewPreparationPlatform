package com.example.interviewprep.service;


//import com.example.interviewprep.dto.AIInterviewSessionResponse;
import com.example.interviewprep.dto.AIInterviewStartResponse;
import com.example.interviewprep.entity.AIDifficulty;
import com.example.interviewprep.entity.AIInterviewSession;
import com.example.interviewprep.entity.AIInterviewQuestion;
import com.example.interviewprep.entity.AIInterviewStatus;
import com.example.interviewprep.repository.AIInterviewSessionRepository;
import org.springframework.stereotype.Service;
import com.example.interviewprep.entity.User;

import java.time.LocalDateTime;

@Service
public class AIInterviewSessionService {

    private final AIInterviewSessionRepository aiInterviewSessionRepository;
    private final AIInterviewQuestionService aiInterviewQuestionService;

    public AIInterviewSessionService(AIInterviewSessionRepository aiInterviewSessionRepository,AIInterviewQuestionService aiInterviewQuestionService){
        this.aiInterviewSessionRepository=aiInterviewSessionRepository;
        this.aiInterviewQuestionService=aiInterviewQuestionService;
    }

    public AIInterviewStartResponse startInterview(User student , String category , AIDifficulty difficulty){
        AIInterviewSession aiInterviewSession=new AIInterviewSession();
        aiInterviewSession.setStudent(student);
        aiInterviewSession.setCategory(category);
        aiInterviewSession.setDifficulty(difficulty);
        aiInterviewSession.setStatus(AIInterviewStatus.IN_PROGRESS);
        aiInterviewSession.setTotalQuestions(5);
        aiInterviewSession.setCurrentQuestion(1);
        aiInterviewSession.setStartedAt(LocalDateTime.now());
        aiInterviewSession.setCompletedAt(null);

        aiInterviewSessionRepository.save(aiInterviewSession);

        AIInterviewQuestion generatedQuestion =
                aiInterviewQuestionService.generateAndSaveQuestion(aiInterviewSession);
        return new AIInterviewStartResponse(
                aiInterviewSession.getId(),
                aiInterviewSession.getCategory(),
                aiInterviewSession.getDifficulty(),
                aiInterviewSession.getStatus(),
                aiInterviewSession.getTotalQuestions(),
                aiInterviewSession.getCurrentQuestion(),
                aiInterviewSession.getStartedAt(),
                generatedQuestion.getId(),
                generatedQuestion.getQuestion(),
                generatedQuestion.getQuestionType()
        );
    }
}
