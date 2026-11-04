package com.example.interviewprep.dto;

public class ProgressSummaryResponse {

    private long totalQuestions;
    private long answeredQuestions;
    private long correctAnswers;
    private long wrongAnswers;
    private double scorePercentage;
    private long remainingQuestions;
    private boolean isCompleted;

    public ProgressSummaryResponse(long totalQuestions,long answeredQuestions,long correctAnswers,long wrongAnswers,double scorePercentage,long remainingQuestions,boolean isCompleted)  {
        this.totalQuestions = totalQuestions;
        this.answeredQuestions = answeredQuestions;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
        this.scorePercentage = scorePercentage;
        this.remainingQuestions = remainingQuestions;
        this.isCompleted = isCompleted;
    }

    public long getTotalQuestions() {
        return totalQuestions;
    }

    public long getAnsweredQuestions() {
        return answeredQuestions;
    }

    public long getCorrectAnswers() {
        return correctAnswers;
    }

    public long getWrongAnswers() {
        return wrongAnswers;
    }

    public double getScorePercentage() {
        return scorePercentage;
    }

    public long getRemainingQuestions() {
        return remainingQuestions;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}