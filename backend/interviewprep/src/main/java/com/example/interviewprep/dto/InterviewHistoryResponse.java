package com.example.interviewprep.dto;

import java.time.LocalDateTime;

public class InterviewHistoryResponse {
    private Long id;
    private String category;
    private String difficultyLevel;
    private String status;
    private Integer score;
    private Integer totalQuestions;
    private Double percentage;
    private String performance;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    public InterviewHistoryResponse(){

    }
    public  InterviewHistoryResponse(Long id, String category, String difficultyLevel, String status, Integer score , Integer totalQuestions, Double percentage, String performance, LocalDateTime startedAt, LocalDateTime completedAt){
        this.id=id;
        this.category=category;
        this.difficultyLevel=difficultyLevel;
        this.status=status;
        this.score=score;
        this.totalQuestions=totalQuestions;
        this.percentage=percentage;
        this.performance=performance;
        this.startedAt=startedAt;
        this.completedAt=completedAt;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public String getDifficultyLevel(){
        return difficultyLevel;
    }
    public void setDifficultyLevel(String difficultyLevel){
        this.difficultyLevel=difficultyLevel;
    }
    public String getStatus(){
        return  status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public Integer getScore(){
        return score;
    }
    public void setScore(Integer score){
        this.score=score;
    }
    public Integer getTotalQuestions(){
        return totalQuestions;
    }
    public void setTotalQuestions(Integer totalQuestions){
        this.totalQuestions=totalQuestions;
    }
    public Double getPercentage(){
        return  percentage;
    }
    public void setPercentage(Double percentage){
        this.percentage=percentage;
    }
    public String getPerformance(){
        return performance;
    }
    public void setPerformance(String performance){
        this.performance=performance;
    }
    public LocalDateTime getStartedAt(){
        return  startedAt;
    }
    public void setStartedAt(LocalDateTime startedAt){
        this.startedAt=startedAt;
    }
    public LocalDateTime getCompletedAt(){
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt){
        this.completedAt=completedAt;
    }
}
