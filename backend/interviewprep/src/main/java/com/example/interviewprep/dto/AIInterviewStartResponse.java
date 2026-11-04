package com.example.interviewprep.dto;

import com.example.interviewprep.entity.AIDifficulty;
import com.example.interviewprep.entity.AIInterviewStatus;

import java.time.LocalDateTime;

public class AIInterviewStartResponse {

    private Long sessionId;
    private String category;
    private AIDifficulty difficulty;
    private AIInterviewStatus status;
    private int totalQuestions;
    private int currentQuestion;
    private LocalDateTime startedAt;

    private Long questionId;
    private String question;
    private String questionType;

    public AIInterviewStartResponse(
            Long sessionId,
            String category,
            AIDifficulty difficulty,
            AIInterviewStatus status,
            int totalQuestions,
            int currentQuestion,
            LocalDateTime startedAt,
            Long questionId,
            String question,
            String questionType) {

        this.sessionId = sessionId;
        this.category = category;
        this.difficulty = difficulty;
        this.status = status;
        this.totalQuestions = totalQuestions;
        this.currentQuestion = currentQuestion;
        this.startedAt = startedAt;
        this.questionId = questionId;
        this.question = question;
        this.questionType = questionType;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public String getCategory() {
        return category;
    }

    public AIDifficulty getDifficulty() {
        return difficulty;
    }

    public AIInterviewStatus getStatus() {
        return status;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionType() {
        return questionType;
    }
}