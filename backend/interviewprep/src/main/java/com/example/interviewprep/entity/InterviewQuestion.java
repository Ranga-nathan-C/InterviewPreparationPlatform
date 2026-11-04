package com.example.interviewprep.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name ="interview_questions")
public class InterviewQuestion{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;
    private String category;
    private String difficultyLevel;
    private String topic;


    public InterviewQuestion(){

    }

    public InterviewQuestion(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer, String category, String difficultyLevel, String topic) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.category = category;
        this.difficultyLevel = difficultyLevel;
        this.topic = topic;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public String getOptionA(){
        return optionA;
    }
    public void setOptionA(String optionA){
        this.optionA=optionA;
    }
    public String getOptionB(){
        return optionB;
    }
    public void setOptionB(String optionB){
        this.optionB=optionB;
    }
    public String getOptionC(){
        return optionC;
    }
    public void setOptionC(String optionC){
        this.optionC=optionC;
    }
    public String getOptionD(){
        return optionD;
    }
    public void setOptionD(String optionD){
        this.optionD=optionD;
    }
    public String getCorrectAnswer(){
        return correctAnswer;
    }
    public void setCorrectAnswer(String correctAnswer){
        this.correctAnswer=correctAnswer;
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
    public String getTopic(){
        return topic;
    }
    public void setTopic(String topic){
        this.topic=topic;
    }
}
