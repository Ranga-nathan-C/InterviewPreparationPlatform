package com.example.interviewprep.dto;

public class AIInterviewReportResponse {

    private Long sessionId;
    private String category;
    private String difficulty;

    private Integer totalQuestions;
    private Integer answeredQuestions;

    private Integer totalScore;
    private Integer maximumScore;
    private Integer percentage;

    private Integer correctAnswers;
    private Integer incorrectAnswers;

    private String performance;

    public AIInterviewReportResponse() {
    }

    public AIInterviewReportResponse(
            Long sessionId,
            String category,
            String difficulty,
            Integer totalQuestions,
            Integer answeredQuestions,
            Integer totalScore,
            Integer maximumScore,
            Integer percentage,
            Integer correctAnswers,
            Integer incorrectAnswers,
            String performance) {

        this.sessionId = sessionId;
        this.category = category;
        this.difficulty = difficulty;
        this.totalQuestions = totalQuestions;
        this.answeredQuestions = answeredQuestions;
        this.totalScore = totalScore;
        this.maximumScore = maximumScore;
        this.percentage = percentage;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.performance = performance;
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

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(Integer incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}