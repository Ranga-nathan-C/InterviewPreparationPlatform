package com.example.interviewprep.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.interviewprep.dto.InterviewQuestionRequest;
import com.example.interviewprep.dto.InterviewQuestionResponse;
import com.example.interviewprep.entity.InterviewQuestion;
import com.example.interviewprep.entity.StudentProgress;
import com.example.interviewprep.exception.AlreadyAnsweredException;
import com.example.interviewprep.exception.QuestionNotFoundException;
import com.example.interviewprep.repository.InterviewQuestionRepository;
import com.example.interviewprep.repository.StudentProgressRepository;

@Service
public class InterviewQuestionService {

        private final InterviewQuestionRepository interviewQuestionRepository;
        private final StudentProgressRepository studentProgressRepository;

        public InterviewQuestionService(InterviewQuestionRepository interviewQuestionRepository, StudentProgressRepository studentProgressRepository){
            this.interviewQuestionRepository=interviewQuestionRepository;
            this.studentProgressRepository=studentProgressRepository;
        }

        public InterviewQuestion createQuestion(InterviewQuestionRequest request){

            InterviewQuestion question = new InterviewQuestion();
            question.setQuestion(request.getQuestion());
            question.setOptionA(request.getOptionA());
            question.setOptionB(request.getOptionB());
            question.setOptionC(request.getOptionC());
            question.setOptionD(request.getOptionD());
            question.setCorrectAnswer(request.getCorrectAnswer());
            question.setCategory(request.getCategory());
            question.setDifficultyLevel(request.getDifficultyLevel());
            question.setTopic(request.getTopic());

            return interviewQuestionRepository.save(question);
        }

        public List<InterviewQuestion> createQuestions(
                List<InterviewQuestionRequest> requests) {

            List<InterviewQuestion> questions = requests.stream()
                                                        .map(request -> new InterviewQuestion(
                                                                request.getQuestion(),
                                                                request.getOptionA(),
                                                                request.getOptionB(),
                                                                request.getOptionC(),
                                                                request.getOptionD(),
                                                                request.getCorrectAnswer(),
                                                                request.getCategory(),
                                                                request.getDifficultyLevel(),
                                                                request.getTopic()
                                                        ))
                                                        .toList();

            return interviewQuestionRepository.saveAll(questions);
        }

        public Page<InterviewQuestion> getAllQuestions(Pageable pageable){
            return interviewQuestionRepository.findAll(pageable);
        }
        public List<InterviewQuestionResponse> findAllForStudents(){
            List<InterviewQuestion> questions = interviewQuestionRepository.findAll();
            return questions.stream()
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
        }

        public boolean checkAnswer(Long questionId,String submittedAnswer, String studentEmail){
            
            Boolean alreadyAnswered = studentProgressRepository.existsByStudentEmailAndQuestionId(studentEmail,questionId);
            if(alreadyAnswered){
                throw new AlreadyAnsweredException("Question already answered by student");
            }

            InterviewQuestion question = interviewQuestionRepository.findById(questionId)
            .orElseThrow(()-> new QuestionNotFoundException("Question not found"));  
            Boolean iscorrect =question.getCorrectAnswer().equalsIgnoreCase(submittedAnswer);
            StudentProgress studentProgress  = new StudentProgress();

            studentProgress.setQuestionId(questionId);
            studentProgress.setStudentEmail(studentEmail);
            studentProgress.setSelectedAnswer(submittedAnswer);
            studentProgress.setCorrect(iscorrect);
            studentProgress.setAnsweredAt(LocalDateTime.now());
            studentProgressRepository.save(studentProgress);
            return iscorrect;
        }

        public List<InterviewQuestion> getUnansweredQuestions(String studentEmail){

            List<Long> answeredQuestionIds = studentProgressRepository
                                                        .findByStudentEmail(studentEmail)
                                                        .stream()
                                                        .map(StudentProgress::getQuestionId)
                                                        .toList();
            return interviewQuestionRepository.findByIdNotIn(answeredQuestionIds);
        }

        public InterviewQuestion getQuestionById(Long questionId){

            return interviewQuestionRepository.findById(questionId)
                            .orElseThrow(()-> new QuestionNotFoundException ("Question not found"));
        }

        public InterviewQuestion updateQuestion(Long questionId , InterviewQuestionRequest request){
            InterviewQuestion question = interviewQuestionRepository.findById(questionId)
                                                                    .orElseThrow(()-> new QuestionNotFoundException("Question not found"));
            question.setQuestion(request.getQuestion());
            question.setOptionA(request.getOptionA());
            question.setOptionB(request.getOptionB());
            question.setOptionC(request.getOptionC());
            question.setOptionD(request.getOptionD());
            question.setCorrectAnswer(request.getCorrectAnswer());
            question.setCategory(request.getCategory());
            question.setCorrectAnswer(request.getCorrectAnswer());
            question.setDifficultyLevel(request.getDifficultyLevel());
            return interviewQuestionRepository.save(question);

        }

        @Transactional
        public void deleteQuestionId(Long questionId){
            InterviewQuestion question = interviewQuestionRepository.findById(questionId)
                                                                    .orElseThrow(()-> new QuestionNotFoundException("Question not found"));
            studentProgressRepository.deleteByQuestionId(questionId);
            interviewQuestionRepository.delete(question);
        }

        public List<InterviewQuestion> getQuestionByCategory(String category){
            return interviewQuestionRepository.findByCategory(category);
        }

        public List<InterviewQuestion> getQuestionByDifficultyLevel(String difficultyLevel){
            return interviewQuestionRepository.findByDifficultyLevel(difficultyLevel);
        }

        public List<InterviewQuestion> getQuestionByCategoryAndDifficultyLevel(String category , String difficultyLevel){
            return interviewQuestionRepository.findByCategoryAndDifficultyLevel(category,difficultyLevel);
        }

        public List<InterviewQuestion> searchQuestions(String keyword){
            return interviewQuestionRepository.findByQuestionContainingIgnoreCase(keyword);
        }
}
