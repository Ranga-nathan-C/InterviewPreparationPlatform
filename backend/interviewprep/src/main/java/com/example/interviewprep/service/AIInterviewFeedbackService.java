package com.example.interviewprep.service;

import java.util.List;
import com.example.interviewprep.dto.AIInterviewFeedbackResponse;
import com.example.interviewprep.entity.AIInterviewAnswer;
import com.example.interviewprep.entity.AIInterviewSession;
import com.example.interviewprep.repository.AIInterviewAnswerRepository;
import com.example.interviewprep.repository.AIInterviewSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class AIInterviewFeedbackService {

    private final AIInterviewSessionRepository aiInterviewSessionRepository;
    private final AIInterviewAnswerRepository aiInterviewAnswerRepository;
    private final AIQuestionService aiQuestionService;

    public AIInterviewFeedbackService(
            AIInterviewSessionRepository aiInterviewSessionRepository,
            AIInterviewAnswerRepository aiInterviewAnswerRepository,
            AIQuestionService aiQuestionService){
        this.aiInterviewSessionRepository=aiInterviewSessionRepository;
        this.aiInterviewAnswerRepository=aiInterviewAnswerRepository;
        this.aiQuestionService=aiQuestionService;
    }

    public AIInterviewFeedbackResponse getPersonalizedFeedback(Long sessionId){
        AIInterviewSession session = aiInterviewSessionRepository
                .findById(sessionId).orElseThrow(()-> new RuntimeException("Interview Session id not found"));

        List<AIInterviewAnswer> answers = aiInterviewAnswerRepository.findBySessionId(sessionId);

        int totalScore = answers.stream().mapToInt(AIInterviewAnswer::getScore).sum();
        int answeredQuestions = answers.size();
        int maximumScore =session.getTotalQuestions()*10;
        int percentage = maximumScore == 0 ? 0 : (totalScore *100)/maximumScore;
        int correctAnswers= (int) answers.stream().filter(AIInterviewAnswer::getCorrect).count();
        int incorrectAnswers = answeredQuestions - correctAnswers;

        return aiQuestionService.generatePersonalizedFeedback(
                session.getCategory(),
                session.getDifficulty().toString(),
                session.getTotalQuestions(),
                answeredQuestions,
                totalScore,
                maximumScore,
                percentage,
                correctAnswers,
                incorrectAnswers
        );
    }
}
