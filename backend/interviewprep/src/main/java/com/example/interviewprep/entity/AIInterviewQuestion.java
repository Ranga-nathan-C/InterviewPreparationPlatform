package com.example.interviewprep.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ai_interview_question")
public class AIInterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private AIInterviewSession session;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expectedAnswer;

    private AIDifficulty difficulty;

    private String questionType;

    public AIInterviewQuestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AIInterviewSession getSession() {
        return session;
    }

    public void setSession(AIInterviewSession session) {
        this.session = session;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getExpectedAnswer() {
        return expectedAnswer;
    }

    public void setExpectedAnswer(String expectedAnswer) {
        this.expectedAnswer = expectedAnswer;
    }

    public AIDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(AIDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}