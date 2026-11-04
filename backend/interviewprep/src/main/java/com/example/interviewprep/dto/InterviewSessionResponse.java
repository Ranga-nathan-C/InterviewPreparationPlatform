package com.example.interviewprep.dto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InterviewSessionResponse {

    private Long id;
    private String category;
    private String difficultyLevel;
    private String status;
    private Integer score;
    private Integer totalQuestions;
    private Integer correctAnswers;
    private Integer incorrectAnswers;
    private Integer unanswered;
    private Double percentage;
    private String performance;
    private List<CategoryPerformanceResponse> categoryPerformance;
    private List<DifficultyPerformanceResponse> difficultyPerformance;
    private List<TopicPerformanceResponse> topicPerformance;
    private List<WeakTopicResponse> weakTopics;
    private PersonalizedFeedbackResponse feedbackResponse;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private List<InterviewQuestionResponse> question = new ArrayList<>();


    public InterviewSessionResponse(Long id,String category,String difficultyLevel,String status,Integer score,Integer totalQuestions,Integer correctAnswers,Integer incorrectAnswers,Integer unanswered,Double percentage,String performance,List<CategoryPerformanceResponse> categoryPerformance,List<DifficultyPerformanceResponse> difficultyPerformance,List<TopicPerformanceResponse> topicPerformance,List<WeakTopicResponse> weakTopics,PersonalizedFeedbackResponse feedbackResponse,LocalDateTime startedAt,LocalDateTime completedAt,List<InterviewQuestionResponse> question){
        this.id=id;
        this.category=category;
        this.difficultyLevel=difficultyLevel;
        this.status=status;
        this.score=score;
        this.totalQuestions=totalQuestions;
        this.correctAnswers=correctAnswers;
        this.incorrectAnswers=incorrectAnswers;
        this.unanswered=unanswered;
        this.percentage=percentage;
        this.performance=performance;
        this.categoryPerformance=categoryPerformance;
        this.difficultyPerformance=difficultyPerformance;
        this.topicPerformance=topicPerformance;
        this.weakTopics=weakTopics;
        this.feedbackResponse=feedbackResponse;
        this.startedAt=startedAt;
        this.completedAt=completedAt;
        this.question=question;

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

    public List<InterviewQuestionResponse> getQuestion() {
        return question;
    }

    public void setQuestion(List<InterviewQuestionResponse> question) {
        this.question = question;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }
    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
    public Integer getCorrectAnswers() {
        return correctAnswers;
    }
    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
    public Integer getIncorrectAnswers() {
        return incorrectAnswers;
    }
    public void setIncorrectAnswers(Integer incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
    public Integer getUnanswered() {
        return unanswered;
    }
    public void setUnanswered(Integer unanswered) {
        this.unanswered = unanswered;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public List<CategoryPerformanceResponse> getCategoryPerformance() {
        return categoryPerformance;
    }

    public void setCategoryPerformance(List<CategoryPerformanceResponse> categoryPerformance) {
        this.categoryPerformance = categoryPerformance;
    }

    public List<DifficultyPerformanceResponse> getDifficultyPerformance() {
        return difficultyPerformance;
    }

    public void setDifficultyPerformance(List<DifficultyPerformanceResponse> difficultyPerformance) {
        this.difficultyPerformance = difficultyPerformance;
    }

    public List<TopicPerformanceResponse> getTopicPerformance() {
        return topicPerformance;
    }

    public void setTopicPerformance(List<TopicPerformanceResponse> topicPerformance) {
        this.topicPerformance = topicPerformance;
    }

    public List<WeakTopicResponse> getWeakTopics() {
        return weakTopics;
    }

    public void setWeakTopics(List<WeakTopicResponse> weakTopics) {
        this.weakTopics = weakTopics;
    }

    public PersonalizedFeedbackResponse getFeedbackResponse() {
        return feedbackResponse;
    }

    public void setFeedbackResponse(PersonalizedFeedbackResponse feedbackResponse) {
        this.feedbackResponse = feedbackResponse;
    }

}
