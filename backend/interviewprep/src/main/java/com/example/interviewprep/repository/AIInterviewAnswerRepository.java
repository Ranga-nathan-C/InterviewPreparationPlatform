package com.example.interviewprep.repository;

import java.util.List;
import com.example.interviewprep.entity.AIInterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AIInterviewAnswerRepository extends JpaRepository <AIInterviewAnswer,Long>{
    List<AIInterviewAnswer> findBySessionId(Long sessionId);
    List<AIInterviewAnswer> findBySessionIdIn(
            Collection<Long> sessionIds);
}
