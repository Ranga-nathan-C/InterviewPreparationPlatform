package com.example.interviewprep.dto;

public class AIInterviewAnswerRequest {

    private Long questionId;

    private String candidateAnswer;

    public AIInterviewAnswerRequest() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getCandidateAnswer() {
        return candidateAnswer;
    }

    public void setCandidateAnswer(String candidateAnswer) {
        this.candidateAnswer = candidateAnswer;
    }
}