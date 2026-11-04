package com.example.interviewprep.entity;


import jakarta.persistence.*;

@Entity
@Table(name="ai_interview_answer")
public class AIInterviewAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="session_id",nullable = false)
    private AIInterviewSession session;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private AIInterviewQuestion question;

    @Column(columnDefinition = "TEXT")
    private String candidateAnswer;

    private Integer score;

    private Boolean correct;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    public AIInterviewAnswer(){

    }

    public AIInterviewAnswer(Long id,AIInterviewSession session,AIInterviewQuestion question,String candidateAnswer,Integer score,Boolean correct , String feedback){
        this.id=id;
        this.session=session;
        this.question=question;
        this.candidateAnswer=candidateAnswer;
        this.score=score;
        this.correct=correct;
        this.feedback=feedback;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public AIInterviewSession getSession(){
        return  session;
    }
    public void setSession(AIInterviewSession session){
        this.session=session;
    }
    public AIInterviewQuestion getQuestion(){
        return question;
    }
    public void setQuestion(AIInterviewQuestion question){
        this.question=question;
    }
    public String getCandidateAnswer(){
        return candidateAnswer;
    }
    public void setCandidateAnswer(String candidateAnswer){
        this.candidateAnswer=candidateAnswer;
    }
    public Integer getScore(){
        return  score;
    }
    public void setScore(Integer score){
        this.score=score;
    }
    public Boolean getCorrect(){
        return correct;
    }
    public void setCorrect(Boolean correct){
        this.correct=correct;
    }
    public String getFeedback(){
        return  feedback;
    }

    public void setFeedback(String feedback){
        this.feedback=feedback;
    }
}
