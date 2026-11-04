package com.example.interviewprep.dto;

public class CategoryPerformanceResponse {

    private String category;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer unanswered;
    private Double percentage;

    public CategoryPerformanceResponse() {
    }

    public CategoryPerformanceResponse(
            String category,
            Integer totalQuestions,
            Integer correctAnswers,
            Integer incorrectAnswers,
            Integer unanswered,
            Double percentage) {

        this.category = category;
        this.totalQuestions = totalQuestions;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.unanswered = unanswered;
        this.percentage = percentage;
    }
    public String getCategory() {
        return category;
    }
    public Integer getTotalQuestions() {
        return totalQuestions;
    }
    public Integer getCorrectAnswers() {
        return correctAnswers;
    }
    public Integer getIncorrectAnswers() {
        return incorrectAnswers;
    }
    public Integer getUnanswered() {
        return unanswered;
    }
    public Double getPercentage() {
        return percentage;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public void setIncorrectAnswers(Integer incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public void setUnanswered(Integer unanswered) {
        this.unanswered = unanswered;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}