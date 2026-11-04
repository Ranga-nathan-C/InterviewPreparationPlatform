package com.example.interviewprep.dto;

public class WeakTopicResponse {
    private String topic;
    private Double percentage;
    private String performance;

    public WeakTopicResponse() {
    }

    public WeakTopicResponse(String topic, Double percentage, String performance) {
        this.topic = topic;
        this.percentage = percentage;
        this.performance = performance;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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