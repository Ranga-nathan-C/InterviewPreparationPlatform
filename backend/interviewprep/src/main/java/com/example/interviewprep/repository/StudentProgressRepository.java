package com.example.interviewprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.example.interviewprep.entity.StudentProgress;
import java.util.List;

public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long>{
    List<StudentProgress> findByStudentEmail(String studentEmail);
    boolean existsByStudentEmailAndQuestionId(String studentEmail,Long questionId);

    @Modifying
    void deleteByQuestionId(Long questionId);
}
