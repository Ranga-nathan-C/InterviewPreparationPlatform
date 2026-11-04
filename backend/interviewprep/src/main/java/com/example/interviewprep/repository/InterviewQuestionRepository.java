package com.example.interviewprep.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.interviewprep.entity.InterviewQuestion;
import java.util.List;


public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion,Long>{
    List<InterviewQuestion> findByIdNotIn(List<Long> questionIds);
    List<InterviewQuestion> findByCategory(String category);
    List<InterviewQuestion> findByDifficultyLevel(String difficultyLevel);
    List<InterviewQuestion> findByCategoryAndDifficultyLevel(String category,String difficultyLevel);
    List<InterviewQuestion> findByQuestionContainingIgnoreCase(String keyword);
}