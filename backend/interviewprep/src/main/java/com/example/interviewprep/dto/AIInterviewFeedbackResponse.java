package com.example.interviewprep.dto;

import java.util.List;

public class AIInterviewFeedbackResponse {

    private String overallFeedback;
    private List<String> strengths;
    private List<String> weakAreas;
    private List<String> recommendations;

    public AIInterviewFeedbackResponse() {
    }

    public AIInterviewFeedbackResponse(
            String overallFeedback,
            List<String> strengths,
            List<String> weakAreas,
            List<String> recommendations) {

        this.overallFeedback = overallFeedback;
        this.strengths = strengths;
        this.weakAreas = weakAreas;
        this.recommendations = recommendations;
    }

    public String getOverallFeedback() {
        return overallFeedback;
    }

    public void setOverallFeedback(String overallFeedback) {
        this.overallFeedback = overallFeedback;
    }

    public List<String> getStrengths() {
        return strengths;
    }

    public void setStrengths(List<String> strengths) {
        this.strengths = strengths;
    }

    public List<String> getWeakAreas() {
        return weakAreas;
    }

    public void setWeakAreas(List<String> weakAreas) {
        this.weakAreas = weakAreas;
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        this.recommendations = recommendations;
    }
}