package com.example.interviewprep.repository;

import com.example.interviewprep.entity.AIInterviewSession;
import com.example.interviewprep.entity.AIInterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AIInterviewSessionRepository extends JpaRepository<AIInterviewSession,Long> {
    List<AIInterviewSession> findByStudentIdAndStatusOrderByCompletedAtDesc(
            Long studentId,
            AIInterviewStatus status);
}
