package com.example.interviewprep.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.interviewprep.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.interviewprep.entity.InterviewAnswer;
import com.example.interviewprep.entity.InterviewQuestion;
import com.example.interviewprep.entity.InterviewSession;
import com.example.interviewprep.entity.User;

import com.example.interviewprep.repository.InterviewAnswerRepository;
import com.example.interviewprep.repository.InterviewQuestionRepository;
import com.example.interviewprep.repository.InterviewSessionRepository;
import com.example.interviewprep.dto.InterviewHistoryResponse;

@Service
public class InterviewSessionService {

        private final UserService userService;
        private final InterviewSessionRepository interviewSessionRepository;
        private final InterviewQuestionRepository interviewQuestionRepository;
        private final InterviewAnswerRepository interviewAnswerRepository;
        

        public InterviewSessionService(
                UserService userService,
                InterviewSessionRepository interviewSessionRepository,
                InterviewQuestionRepository interviewQuestionRepository,
                InterviewAnswerRepository interviewAnswerRepository) {

                this.userService = userService;
                this.interviewSessionRepository = interviewSessionRepository;
                this.interviewQuestionRepository = interviewQuestionRepository;
                this.interviewAnswerRepository = interviewAnswerRepository;
        }

        public InterviewSessionResponse startInterview(
                String email,
                String category,
                String difficultyLevel) {

                User student = userService.findByEmail(email);

                List<InterviewQuestion> questions =
                        interviewQuestionRepository.findByCategoryAndDifficultyLevel(
                                category,
                                difficultyLevel
                        );

                if (questions.size() < 5) {
                throw new IllegalArgumentException(
                        "Not enough questions available for this interview"
                );
                }

                questions = questions.subList(0, 5);

                InterviewSession session = new InterviewSession();

                session.setStudent(student);
                session.setCategory(category);
                session.setDifficultyLevel(difficultyLevel);
                session.setQuestions(questions);
                session.setStatus("IN_PROGRESS");
                session.setScore(0);
                session.setStartedAt(LocalDateTime.now());

                InterviewSession interviewSession =interviewSessionRepository.save(session);

                List<InterviewQuestionResponse> questionResponses =
                        interviewSession.getQuestions()
                                .stream()
                                .map(question -> new InterviewQuestionResponse(
                                        question.getId(),
                                        question.getQuestion(),
                                        question.getOptionA(),
                                        question.getOptionB(),
                                        question.getOptionC(),
                                        question.getOptionD(),
                                        question.getCategory(),
                                        question.getTopic(),
                                        question.getDifficultyLevel()
                                ))
                                .toList();

                return new InterviewSessionResponse(
                        interviewSession.getId(),
                        interviewSession.getCategory(),
                        interviewSession.getDifficultyLevel(),
                        interviewSession.getStatus(),
                        interviewSession.getScore(),
                        5,
                        0,
                        0,
                        0,
                        0.0,
                        "POOR",
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new ArrayList<>(),
                        null,
                        interviewSession.getStartedAt(),
                        interviewSession.getCompletedAt(),
                        questionResponses
                );
        }
        public void submitAnswer(Long sessionId,SubmitAnswerRequest request){
                InterviewSession session = interviewSessionRepository
                                                        .findById(sessionId)
                                                        .orElseThrow(()-> 
                                                        new IllegalArgumentException("Interview Session not found"));
                InterviewQuestion question = interviewQuestionRepository
                                                        .findById(request.getQuestionId())
                                                        .orElseThrow(()->
                                                        new IllegalArgumentException("Interview Question not found"));

                if (!session.getQuestions().contains(question)) {
                throw new IllegalArgumentException(
                        "Question does not belong to this interview session"
                );
                }
                if (interviewAnswerRepository.existsBySessionIdAndQuestionId(
                        sessionId,
                        request.getQuestionId())) {

                throw new IllegalArgumentException(
                        "Question already answered"
                );
                }
                boolean correct = question.getCorrectAnswer()
                .equalsIgnoreCase(request.getAnswer());

                InterviewAnswer answer = new InterviewAnswer();

                answer.setSession(session);
                answer.setQuestion(question);
                answer.setSelectedAnswer(request.getAnswer());
                answer.setCorrect(correct);

                interviewAnswerRepository.save(answer);

                if (correct) {
                session.setScore(session.getScore() + 1);
                interviewSessionRepository.save(session);
                }
        }
        public Page<InterviewHistoryResponse> getInterviewHistory(Long studentId, Pageable pageable){
                Page<InterviewSession> sessions = interviewSessionRepository.findByStudentIdAndStatusOrderByStartedAtDesc(studentId,"COMPLETED",pageable);
                return sessions.map(session ->{
                                int totalQuestions = session.getQuestions().size();
                                int score = session.getScore()!=null ? session.getScore():0;
                                double percentage = totalQuestions > 0 ?(double) score / totalQuestions * 100:0.0;
                                String performance;
                                if(percentage>=80){
                                        performance="EXCELLENT";
                                }
                                else if (percentage>=60) {
                                        performance = "GOOD";
                                }
                                else if(percentage>=40){
                                        performance = "AVERAGE";
                                }
                                else{
                                        performance="NOT BAD , TRY AGAIN";
                                }
                                return new InterviewHistoryResponse(
                                        session.getId(),
                                        session.getCategory(),
                                        session.getDifficultyLevel(),
                                        session.getStatus(),
                                        score,
                                        totalQuestions,
                                        percentage,
                                        performance,
                                        session.getStartedAt(),
                                        session.getCompletedAt()
                                );
                });
        }
        public InterviewSessionResponse completeInterview(Long sessionId){
                InterviewSession session = interviewSessionRepository
                                                                .findById(sessionId)
                                                                .orElseThrow(()-> new IllegalArgumentException("Interview Session is not found to complete"));
                if(!session.getStatus().equals("IN_PROGRESS")){
                throw new IllegalArgumentException("Session is already completed");
                }

                List<InterviewAnswer> answers = interviewAnswerRepository.findBySessionId(sessionId);

                int totalQuestions = session.getQuestions().size();

                int correctAnswers = (int) answers.stream()
                        .filter(InterviewAnswer::isCorrect)
                        .count();

                int incorrectAnswers = answers.size() - correctAnswers;

                int unanswered = totalQuestions - answers.size();
                double percentage =(double )correctAnswers / totalQuestions*100;
                Map<String, List<InterviewQuestion>> questionsByCategory =
                session.getQuestions()
                        .stream()
                        .collect(Collectors.groupingBy(InterviewQuestion::getCategory));

                Map<String,List<InterviewQuestion>> questionsByDifficulty = 
                session.getQuestions()
                        .stream()
                        .collect(Collectors.groupingBy(InterviewQuestion::getDifficultyLevel));
                
                Map<String, List<InterviewAnswer>> answersByTopic = 
                answers.stream()
                        .collect(Collectors.groupingBy(answer -> answer.getQuestion().getTopic()));

                List<TopicPerformanceResponse> topicPerformance = 
                answersByTopic.entrySet()
                                .stream()
                                .map(entry -> {
                                        String topic = entry.getKey();
                                        List<InterviewAnswer> topicAnswers =  entry.getValue();

                                        int topicTotalQuestions = (int) session.getQuestions()
                                                .stream()
                                                .filter(question -> question.getTopic().equals(topic))
                                                .count();

                                        int topicCorrectAnswers = (int) topicAnswers.stream()
                                                .filter(InterviewAnswer::isCorrect)
                                                .filter(answer-> answer.getQuestion().getTopic().equals(topic)).count();
                                        int topicAnswered = (int) answers.stream()
                                                .filter(answer-> answer.getQuestion().getTopic().equals(topic)).count();
                                        int topicIncorrectAnswers = topicAnswered - topicCorrectAnswers;

                                        int topicUnanswered = topicTotalQuestions - topicAnswered;

                                        double topicPercentage = (double) topicCorrectAnswers / topicTotalQuestions*100;
                                        String topicPerformances;
                                        if(topicPercentage>=80){
                                                topicPerformances="EXCELLENT";
                                        }
                                        else if(topicPercentage>=60){
                                                topicPerformances="GOOD";
                                        }
                                        else if(topicPercentage>=40){
                                                topicPerformances="AVERAGE";
                                        }
                                        else{
                                                topicPerformances="POOR";
                                        }

                                        return new TopicPerformanceResponse(
                                                topic,
                                                topicTotalQuestions,
                                                topicCorrectAnswers,
                                                topicIncorrectAnswers,
                                                topicUnanswered,
                                                topicPercentage,
                                                topicPerformances
                                        );
                                }).toList();
                List<WeakTopicResponse> weakTopics = topicPerformance.stream()
                                .filter(topic-> topic.getPercentage() < 60)
                                .map(topic -> new WeakTopicResponse(
                                        topic.getTopic(),
                                        topic.getPercentage(),
                                        topic.getPerformance()
                                ))
                                .toList();
                String overallFeedback;

                if (percentage >= 80) {
                        overallFeedback = "Excellent performance! You have a strong understanding of the interview topics.";
                }
                else if (percentage >= 60) {
                        overallFeedback = "Good performance! You have a solid understanding, but there are some areas you can improve.";
                }
                else if (percentage >= 40) {
                        overallFeedback = "Average performance. You have a basic understanding, but you should focus on improving your weak areas.";
                }
                else {
                        overallFeedback = "Poor performance. You need to strengthen your fundamentals and practice more before your next interview.";
                }
                List<String> strengths = topicPerformance.stream()
                        .filter(topic -> topic.getPercentage() >= 60)
                        .map(topic -> topic.getTopic())
                        .toList();
                List<String> weakAreas = topicPerformance.stream()
                        .filter(topic -> topic.getPercentage() < 60)
                        .map(topic -> topic.getTopic())
                        .toList();
                List<String> recommendations = weakAreas.stream()
                        .map(area -> "Focus on improving your knowledge of " + area)
                        .toList();

                PersonalizedFeedbackResponse feedbackResponse = new PersonalizedFeedbackResponse(
                        overallFeedback,
                        strengths,
                        weakAreas,
                        recommendations
                );
                List<CategoryPerformanceResponse> categoryPerformance =
                questionsByCategory.entrySet()
                        .stream()
                        .map(entry -> {

                                String category = entry.getKey();
                                List<InterviewQuestion> categoryQuestions = entry.getValue();

                                int categoryTotal = categoryQuestions.size();

                                int categoryCorrect = (int) answers.stream()
                                        .filter(InterviewAnswer::isCorrect)
                                        .filter(answer ->
                                                answer.getQuestion().getCategory()
                                                        .equals(category))
                                        .count();

                                int categoryAnswered = (int) answers.stream()
                                        .filter(answer ->
                                                answer.getQuestion().getCategory()
                                                        .equals(category))
                                        .count();

                                int categoryIncorrect =categoryAnswered - categoryCorrect;

                                int categoryUnanswered =categoryTotal - categoryAnswered;

                                double categoryPercentage =(double) categoryCorrect / categoryTotal * 100;

                        return new CategoryPerformanceResponse(
                                        category,
                                        categoryTotal,
                                        categoryCorrect,
                                        categoryIncorrect,
                                        categoryUnanswered,
                                        categoryPercentage
                        );
                        })
                        .toList();

                List<DifficultyPerformanceResponse> difficultyPerformance =
                questionsByDifficulty.entrySet()
                .stream()
                .map(entry -> {

                        String difficulty = entry.getKey();

                        List<InterviewQuestion> difficultyQuestions =
                                entry.getValue();

                        int difficultyTotal = difficultyQuestions.size();

                        int difficultyCorrect = (int) answers.stream()
                                .filter(InterviewAnswer::isCorrect)
                                .filter(answer ->
                                        answer.getQuestion()
                                                .getDifficultyLevel()
                                                .equals(difficulty))
                                .count();

                        int difficultyAnswered = (int) answers.stream()
                                .filter(answer ->
                                        answer.getQuestion()
                                                .getDifficultyLevel()
                                                .equals(difficulty))
                                .count();

                        int difficultyIncorrect =
                                difficultyAnswered - difficultyCorrect;

                        int difficultyUnanswered =
                                difficultyTotal - difficultyAnswered;

                        double difficultyPercentage =
                                (double) difficultyCorrect
                                / difficultyTotal * 100;

                        String difficultyPerformanceLevel;

                        if (difficultyPercentage >= 80) {
                                difficultyPerformanceLevel = "EXCELLENT";
                        }
                        else if (difficultyPercentage >= 60) {
                                difficultyPerformanceLevel = "GOOD";
                        }
                        else if (difficultyPercentage >= 40) {
                                difficultyPerformanceLevel = "AVERAGE";
                        }
                        else {
                                difficultyPerformanceLevel = "POOR";
                        }

                        return new DifficultyPerformanceResponse(
                                difficulty,
                                difficultyTotal,
                                difficultyCorrect,
                                difficultyIncorrect,
                                difficultyUnanswered,
                                difficultyPercentage,
                                difficultyPerformanceLevel
                        );
                }).toList();
                String performance;
                if(percentage>=80){
                        performance="EXCELLENT";
                }
                else if(percentage>=60){
                        performance="GOOD";
                }
                else if(percentage>=40){
                        performance="AVERAGE";
                }
                else{
                        performance="POOR";
                }
                session.setStatus("COMPLETED");
                session.setCompletedAt(LocalDateTime.now());


                InterviewSession completed = interviewSessionRepository.save(session);

                List<InterviewQuestionResponse> questionResponses = completed.getQuestions()
                                                                        .stream()   
                                                                        .map(question-> new InterviewQuestionResponse(
                                                                                question.getId(),
                                                                                question.getQuestion(),
                                                                                question.getOptionA(),
                                                                                question.getOptionB(),
                                                                                question.getOptionC(),
                                                                                question.getOptionD(),
                                                                                question.getCategory(),
                                                                                question.getTopic(),
                                                                                question.getDifficultyLevel()
                                                                        )).toList();
                return new InterviewSessionResponse(
                        completed.getId(),
                        completed.getCategory(),
                        completed.getDifficultyLevel(),
                        completed.getStatus(),
                        completed.getScore(),
                        totalQuestions,
                        correctAnswers,
                        incorrectAnswers,
                        unanswered,
                        percentage,
                        performance,
                        categoryPerformance,
                        difficultyPerformance,
                        topicPerformance,
                        weakTopics,
                        feedbackResponse,
                        completed.getStartedAt(),
                        completed.getCompletedAt(),
                        questionResponses
                );
        }
}