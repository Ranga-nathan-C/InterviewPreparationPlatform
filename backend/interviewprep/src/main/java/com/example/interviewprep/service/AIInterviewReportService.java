package com.example.interviewprep.service;

import java.util.List;
import com.example.interviewprep.dto.AIInterviewReportResponse;
import com.example.interviewprep.entity.AIInterviewAnswer;
import com.example.interviewprep.repository.AIInterviewAnswerRepository;
import com.example.interviewprep.repository.AIInterviewSessionRepository;
import com.example.interviewprep.entity.AIInterviewSession;
import org.springframework.stereotype.Service;

@Service
public class AIInterviewReportService {

    private final AIInterviewSessionRepository aiInterviewSessionRepository;
    private final AIInterviewAnswerRepository aiInterviewAnswerRepository;

    public AIInterviewReportService(AIInterviewSessionRepository aiInterviewSessionRepository,AIInterviewAnswerRepository aiInterviewAnswerRepository){
        this.aiInterviewSessionRepository=aiInterviewSessionRepository;
        this.aiInterviewAnswerRepository=aiInterviewAnswerRepository;
    }

    public AIInterviewReportResponse getReport(Long sessionId){
        AIInterviewSession session = aiInterviewSessionRepository
                .findById(sessionId).orElseThrow(()-> new RuntimeException("Interview Session not found"));

        List<AIInterviewAnswer> answers = aiInterviewAnswerRepository.findBySessionId(sessionId);

        int totalScore = answers.stream().mapToInt(AIInterviewAnswer::getScore).sum();

        int answeredQuestions = answers.size();

        int maximumScore = session.getTotalQuestions()*10;

        int percentage = maximumScore == 0 ? 0 : (totalScore*100) / maximumScore;

        int correctAnswers = (int)answers.stream().filter(AIInterviewAnswer::getCorrect).count();

        int incorrectAnswers = answeredQuestions - correctAnswers;

        String performance;
        if (percentage >= 80) {
            performance = "EXCELLENT";
        } else if (percentage >= 60) {
            performance = "GOOD";
        } else if (percentage >= 40) {
            performance = "AVERAGE";
        } else {
            performance = "POOR";
        }

        return new AIInterviewReportResponse(
                session.getId(),
                session.getCategory(),
                session.getDifficulty().toString(),
                session.getTotalQuestions(),
                answeredQuestions,
                totalScore,
                maximumScore,
                percentage,
                correctAnswers,
                incorrectAnswers,
                performance
        );
    }
}
