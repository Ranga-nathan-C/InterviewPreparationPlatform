package com.example.interviewprep.dto;

import com.example.interviewprep.entity.AIDifficulty;
import com.example.interviewprep.entity.AIInterviewStatus;

import java.time.LocalDateTime;

public class AIInterviewSessionResponse {

    private Long id;
    private String category;
    private AIDifficulty difficulty;
    private AIInterviewStatus status;
    private int totalQuestions;
    private int currentQuestion;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public AIInterviewSessionResponse(
            Long id,
            String category,
            AIDifficulty difficulty,
            AIInterviewStatus status,
            int totalQuestions,
            int currentQuestion,
            LocalDateTime startedAt,
            LocalDateTime completedAt) {

        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.status = status;
        this.totalQuestions = totalQuestions;
        this.currentQuestion = currentQuestion;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AIDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(AIDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public AIInterviewStatus getStatus() {
        return status;
    }

    public void setStatus(AIInterviewStatus status) {
        this.status = status;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}