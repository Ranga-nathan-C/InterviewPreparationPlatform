package com.example.interviewprep.dto;

import com.example.interviewprep.entity.AIInterviewStatus;

public class AIInterviewAnswerResponse {

    private Long id;
    private Long questionId;
    private String candidateAnswer;
    private Integer score;
    private Boolean correct;
    private String feedback;
    private Long nextQuestionId;
    private String nextQuestion;
    private String nextQuestionType;
    private AIInterviewStatus sessionStatus;

    public AIInterviewAnswerResponse() {
    }

    public AIInterviewAnswerResponse(
            Long id,
            Long questionId,
            String candidateAnswer,
            Integer score,
            Boolean correct,
            String feedback,
            Long nextQuestionId,
            String nextQuestion,
            String nextQuestionType,
            AIInterviewStatus sessionStatus) {

        this.id = id;
        this.questionId = questionId;
        this.candidateAnswer = candidateAnswer;
        this.score = score;
        this.correct = correct;
        this.feedback = feedback;
        this.nextQuestionId=nextQuestionId;
        this.nextQuestion=nextQuestion;
        this.nextQuestionType=nextQuestionType;
        this.sessionStatus=sessionStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNextQuestionId() {
        return nextQuestionId;
    }

    public void setNextQuestionId(Long nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }

    public String getNextQuestion() {
        return nextQuestion;
    }

    public void setNextQuestion(String nextQuestion) {
        this.nextQuestion = nextQuestion;
    }

    public String getNextQuestionType() {
        return nextQuestionType;
    }

    public void setNextQuestionType(String nextQuestionType) {
        this.nextQuestionType = nextQuestionType;
    }

    public AIInterviewStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(AIInterviewStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }
}