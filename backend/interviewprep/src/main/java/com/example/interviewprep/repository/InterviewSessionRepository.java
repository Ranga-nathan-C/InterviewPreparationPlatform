package com.example.interviewprep.repository;
import  java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.interviewprep.entity.InterviewSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface InterviewSessionRepository extends JpaRepository<InterviewSession,Long> {
    Page<InterviewSession> findByStudentIdAndStatusOrderByStartedAtDesc(Long studentId , String status,Pageable pageable);
}
