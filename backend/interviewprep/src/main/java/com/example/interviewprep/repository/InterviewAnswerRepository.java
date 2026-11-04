package com.example.interviewprep.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.interviewprep.entity.InterviewAnswer;
import java.util.List;

public interface InterviewAnswerRepository extends JpaRepository<InterviewAnswer,Long> {
    boolean existsBySessionIdAndQuestionId(Long sessionId, Long questionId);
    List<InterviewAnswer> findBySessionId(Long sessionId);
}
