package com.example.interviewprep.dto;
import java.util.List;
import com.example.interviewprep.entity.AIDifficulty;

public class AIQuestionResponse {
    private String question;

    private String expectedAnswer;

    private List<String> keyConcepts;

    private AIDifficulty difficulty;

    private String questionType;

    public AIQuestionResponse(){

    }
    public AIQuestionResponse(String question, String expectedAnswer, List<String> keyConcepts, AIDifficulty difficulty,String questionType){
        this.question=question;
        this.expectedAnswer=expectedAnswer;
        this.keyConcepts=keyConcepts;
        this.difficulty=difficulty;
        this.questionType=questionType;
    }

    public String getQuestion() {

        return question;
    }

    public String getExpectedAnswer() {

        return expectedAnswer;
    }

    public AIDifficulty getDifficulty() {

        return difficulty;
    }

    public List<String> getKeyConcepts() {

        return keyConcepts;
    }

    public String getQuestionType() {
        return questionType;
    }
}
