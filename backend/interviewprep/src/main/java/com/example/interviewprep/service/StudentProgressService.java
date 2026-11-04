package com.example.interviewprep.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.interviewprep.dto.ProgressSummaryResponse;
import com.example.interviewprep.entity.StudentProgress;
import com.example.interviewprep.repository.InterviewQuestionRepository;
import com.example.interviewprep.repository.StudentProgressRepository;

@Service
public class StudentProgressService {
    private final StudentProgressRepository studentProgressRepository;
    private final InterviewQuestionRepository interviewQuestionRepository;

    public StudentProgressService(StudentProgressRepository studentProgressRepository, InterviewQuestionRepository interviewQuestionRepository) {
        this.studentProgressRepository = studentProgressRepository;
        this.interviewQuestionRepository = interviewQuestionRepository;
    }

    public List<StudentProgress> getStudentProgress(String studentEmail) {
        return studentProgressRepository.findByStudentEmail(studentEmail);
    }
    public List<StudentProgress> getAllStudentProgress() {
        return studentProgressRepository.findAll();
    }

    public ProgressSummaryResponse getProgressSummary(String studentEmail){
        List<StudentProgress> allProgress = studentProgressRepository.findByStudentEmail(studentEmail);
        long totalQuestions = interviewQuestionRepository.count();
        long answeredQuestions = allProgress.size();
        long correctAnswers = allProgress.stream().filter(StudentProgress::isCorrect).count();
        long wrongAnswers=answeredQuestions-correctAnswers;
        double scorePercentage = 0;
        if(answeredQuestions > 0){
            scorePercentage=(double) correctAnswers / answeredQuestions * 100;
        }
        long remainingQuestions = totalQuestions - answeredQuestions;
        boolean isCompleted = remainingQuestions == 0;
        return new ProgressSummaryResponse(totalQuestions, answeredQuestions, correctAnswers, wrongAnswers, scorePercentage, remainingQuestions, isCompleted);
    }

    public List<Long> getAnsweredQuestionIds(String studentEmail){

        return studentProgressRepository
                .findByStudentEmail(studentEmail)
                .stream()
                .map(StudentProgress::getQuestionId)
                .toList();

    }

}
