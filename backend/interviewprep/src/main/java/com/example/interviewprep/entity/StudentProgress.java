package com.example.interviewprep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_progress")
public class StudentProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;

    private Long questionId;

    private String selectedAnswer;

    private boolean correct;

    private LocalDateTime answeredAt;

    public StudentProgress() {
    }

    public Long getId() {
        return id;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public LocalDateTime getAnsweredAt() {
        return answeredAt;
    }

    public void setAnsweredAt(LocalDateTime answeredAt) {
        this.answeredAt = answeredAt;
    }
}