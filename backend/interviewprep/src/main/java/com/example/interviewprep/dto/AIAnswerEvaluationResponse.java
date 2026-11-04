package com.example.interviewprep.dto;

public class AIAnswerEvaluationResponse {

    private Integer score;

    private Boolean correct;

    private String feedback;

    public AIAnswerEvaluationResponse() {
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}