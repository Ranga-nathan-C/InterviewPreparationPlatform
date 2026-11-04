package com.example.interviewprep.dto;

public class TopicPerformanceResponse {
    private String topic;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer unanswered;
    private Double percentage;
    private String performance;

    public TopicPerformanceResponse() {
    }

    public TopicPerformanceResponse(String topic, Integer totalQuestions, Integer correctAnswers, Integer incorrectAnswers, Integer unanswered, Double percentage, String performance){
        this.topic=topic;
        this.totalQuestions=totalQuestions;
        this.correctAnswers=correctAnswers;
        this.incorrectAnswers=incorrectAnswers;
        this.unanswered=unanswered;
        this.percentage=percentage;
        this.performance=performance;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
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

    public Integer getUnanswered() {
        return unanswered;
    }

    public void setUnanswered(Integer unanswered) {
        this.unanswered = unanswered;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }
}
