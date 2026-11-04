package com.example.interviewprep.dto;


public class InterviewQuestionResponse {
    

    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String category;
    private String topic;
    private String difficultyLevel;
    public InterviewQuestionResponse() {
    }

    public InterviewQuestionResponse(Long id, String question, String optionA, String optionB, String optionC, String optionD, String category, String topic, String difficultyLevel) {
        this.id = id;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.category = category;
        this.topic = topic;
        this.difficultyLevel = difficultyLevel;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQuestions() {
        return question;
    }
    public void setQuestions(String question) {
        this.question = question;
    }
    public String getOptionA() {
        return optionA;
    }
    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }
    public String getOptionB() {
        return optionB;
    }
    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }
    public String getOptionC() {
        return optionC;
    }
    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }
    public String getOptionD() {
        return optionD;
    }
    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDifficultyLevel() {
        return difficultyLevel;
    }
    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

}
