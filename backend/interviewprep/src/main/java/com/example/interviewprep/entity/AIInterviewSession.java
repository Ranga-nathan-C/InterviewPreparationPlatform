package com.example.interviewprep.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="ai_interview_session")
public class AIInterviewSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name="studentId",nullable = false)
    private User student;

    private String category;

    @Enumerated(EnumType.STRING)
    private AIDifficulty difficulty;

    @Enumerated(EnumType.STRING)

    private AIInterviewStatus status;

    private int totalQuestions;

    private int currentQuestion;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;


    public AIInterviewSession(){

    }
    public AIInterviewSession(Long id,User student,String category,AIDifficulty difficulty,AIInterviewStatus status,int totalQuestions,int currentQuestion,LocalDateTime startedAt, LocalDateTime completedAt){
        this.id=id;
        this.student=student;
        this.category=category;
        this.difficulty=difficulty;
        this.status=status;
        this.totalQuestions=totalQuestions;
        this.currentQuestion = currentQuestion;
        this.startedAt =startedAt;
        this.completedAt=completedAt;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public User getStudent(){
        return student;
    }

    public void setStudent(User student){
        this.student=student;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category=category;
    }

    public AIDifficulty getDifficulty(){
        return difficulty;
    }

    public void setDifficulty(AIDifficulty difficulty){
        this.difficulty=difficulty;
    }

    public AIInterviewStatus getStatus(){
        return  status;
    }

    public void setStatus(AIInterviewStatus status){
        this.status=status;
    }

    public int getTotalQuestions(){
        return  totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions){
        this.totalQuestions=totalQuestions;
    }

    public int getCurrentQuestion(){
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion){
        this.currentQuestion=currentQuestion;
    }

    public LocalDateTime getStartedAt(){
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt){
        this.startedAt=startedAt;
    }

    public LocalDateTime getCompletedAt(){
        return  completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt){
        this.completedAt=completedAt;
    }

}
