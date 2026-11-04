package com.example.interviewprep.dto;

import java.time.LocalDateTime;

public class AIInterviewHistoryResponse {

    private Long sessionId;
    private String category;
    private String difficulty;
    private Integer totalQuestions;
    private Integer answeredQuestions;
    private Integer totalScore;
    private Integer maximumScore;
    private Integer percentage;
    private String performance;
    private LocalDateTime completedAt;

    public AIInterviewHistoryResponse() {
    }

    public AIInterviewHistoryResponse(
            Long sessionId,
            String category,
            String difficulty,
            Integer totalQuestions,
            Integer answeredQuestions,
            Integer totalScore,
            Integer maximumScore,
            Integer percentage,
            String performance,
            LocalDateTime completedAt) {

        this.sessionId = sessionId;
        this.category = category;
        this.difficulty = difficulty;
        this.totalQuestions = totalQuestions;
        this.answeredQuestions = answeredQuestions;
        this.totalScore = totalScore;
        this.maximumScore = maximumScore;
        this.percentage = percentage;
        this.performance = performance;
        this.completedAt = completedAt;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Integer getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Integer answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(Integer maximumScore) {
        this.maximumScore = maximumScore;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}