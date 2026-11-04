package com.example.interviewprep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.ArrayList;


@Entity
@Table(name = "interview_sessions")
public class InterviewSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private User student;

    private String category;

    private String difficultyLevel;

    private String status;

    private Integer score;

    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    public InterviewSession(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
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
        return status;
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
    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @ManyToMany
    private List<InterviewQuestion> questions = new ArrayList<>();

    public List<InterviewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<InterviewQuestion> questions) {
        this.questions = questions;
    }
}