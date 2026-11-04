package com.example.interviewprep.service;

import java.util.*;
import com.example.interviewprep.dto.AIInterviewHistoryResponse;
import com.example.interviewprep.entity.AIInterviewAnswer;
import com.example.interviewprep.entity.AIInterviewSession;
import com.example.interviewprep.entity.AIInterviewStatus;
import com.example.interviewprep.repository.AIInterviewAnswerRepository;
import com.example.interviewprep.repository.AIInterviewSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class AIInterviewHistoryService {

    private final AIInterviewSessionRepository aiInterviewSessionRepository;
    private final AIInterviewAnswerRepository aiInterviewAnswerRepository;

    public AIInterviewHistoryService(AIInterviewSessionRepository aiInterviewSessionRepository,
                                     AIInterviewAnswerRepository aiInterviewAnswerRepository){
        this.aiInterviewAnswerRepository=aiInterviewAnswerRepository;
        this.aiInterviewSessionRepository=aiInterviewSessionRepository;
    }

    public List<AIInterviewHistoryResponse> getInterviewHistory(Long studentId){
        List<AIInterviewSession> sessions = aiInterviewSessionRepository
                .findByStudentIdAndStatusOrderByCompletedAtDesc(
                        studentId,
                        AIInterviewStatus.COMPLETED);

        List<AIInterviewHistoryResponse> history = new ArrayList<>();

        for(AIInterviewSession session : sessions) {
            List<AIInterviewAnswer> answers = aiInterviewAnswerRepository.findBySessionId(session.getId());
            int totalScore = answers.stream().mapToInt(AIInterviewAnswer::getScore).sum();
            int answeredQuestions = answers.size();
            int maximumScore = session.getTotalQuestions() * 10;
            int percentage = maximumScore == 0 ? 0 : (totalScore * 100) / maximumScore;
            int correctAnswers = (int) answers.stream().filter(AIInterviewAnswer::getCorrect).count();
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

            AIInterviewHistoryResponse response = new AIInterviewHistoryResponse(
                    session.getId(),
                    session.getCategory(),
                    session.getDifficulty().toString(),
                    session.getTotalQuestions(),
                    answeredQuestions,
                    totalScore,
                    maximumScore,
                    percentage,
                    performance,
                    session.getCompletedAt()
            );
            history.add(response);
        }
        return history;
    }
}
