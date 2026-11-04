    package com.example.interviewprep.service;

    import com.example.interviewprep.dto.AIAnswerEvaluationResponse;
    import com.example.interviewprep.dto.AIInterviewAnswerRequest;
    import com.example.interviewprep.dto.AIInterviewAnswerResponse;
    import com.example.interviewprep.entity.AIInterviewStatus;
    import com.example.interviewprep.entity.AIInterviewAnswer;
    import com.example.interviewprep.entity.AIInterviewQuestion;
    import com.example.interviewprep.entity.AIInterviewSession;
    import com.example.interviewprep.repository.AIInterviewAnswerRepository;
    import com.example.interviewprep.repository.AIInterviewQuestionRepository;
    import com.example.interviewprep.repository.AIInterviewSessionRepository;
    import org.springframework.stereotype.Service;
    import com.example.interviewprep.entity.User;

    @Service
    public class AIInterviewAnswerService {

        private final AIInterviewAnswerRepository aiInterviewAnswerRepository;
        private final AIInterviewQuestionRepository aiInterviewQuestionRepository;
        private final AIQuestionService aiQuestionService;
        private final AIInterviewSessionRepository aiInterviewSessionRepository;
        private final UserService userService;
        private final AIInterviewQuestionService aiInterviewQuestionService;

        public AIInterviewAnswerService(
                AIInterviewAnswerRepository aiInterviewAnswerRepository,
                AIInterviewQuestionRepository aiInterviewQuestionRepository,
                AIQuestionService aiQuestionService,
                AIInterviewSessionRepository aiInterviewSessionRepository,
                UserService userService,
                AIInterviewQuestionService aiInterviewQuestionService) {

            this.aiInterviewAnswerRepository = aiInterviewAnswerRepository;
            this.aiInterviewQuestionRepository = aiInterviewQuestionRepository;
            this.aiQuestionService = aiQuestionService;
            this.aiInterviewSessionRepository=aiInterviewSessionRepository;
            this.userService=userService;
            this.aiInterviewQuestionService=aiInterviewQuestionService;
        }

        public AIInterviewAnswerResponse submitAnswer(Long sessionId, String email, AIInterviewAnswerRequest aiInterviewAnswerRequest){
            User student = userService.findByEmail(email);
            AIInterviewQuestion question = aiInterviewQuestionRepository
                    .findById(aiInterviewAnswerRequest
                            .getQuestionId()).orElseThrow(()-> new RuntimeException("Question not found"));

            AIInterviewSession session = aiInterviewSessionRepository
                    .findById(sessionId)
                    .orElseThrow(()-> new RuntimeException("Interview Session not found"));

            if (!session.getStudent().getId().equals(student.getId())) {
                throw new RuntimeException(
                        "You are not allowed to answer this interview session");
            }
            if (!question.getSession().getId().equals(session.getId())) {
                throw new RuntimeException("Question does not belong to this interview session");
            }

            if (session.getStatus() != AIInterviewStatus.IN_PROGRESS) {
                throw new RuntimeException(
                        "Interview session is already completed");
            }

            AIAnswerEvaluationResponse aiAnswerEvaluationResponse = aiQuestionService
                    .evaluateAnswer(question.getQuestion(),question.getExpectedAnswer(),aiInterviewAnswerRequest.getCandidateAnswer());

            AIInterviewAnswer answer = new AIInterviewAnswer();
            answer.setSession(session);
            answer.setQuestion(question);
            answer.setCandidateAnswer(aiInterviewAnswerRequest.getCandidateAnswer());
            answer.setScore(aiAnswerEvaluationResponse.getScore());
            answer.setCorrect(aiAnswerEvaluationResponse.getCorrect());
            answer.setFeedback(aiAnswerEvaluationResponse.getFeedback());

            AIInterviewAnswer savedAnswer = aiInterviewAnswerRepository.save(answer);
            AIInterviewQuestion nextQuestion = null;
            if (session.getCurrentQuestion() < session.getTotalQuestions()) {

                session.setCurrentQuestion(
                        session.getCurrentQuestion() + 1
                );

                aiInterviewSessionRepository.save(session);
                nextQuestion =
                        aiInterviewQuestionService.generateAndSaveQuestion(session);

            } else {

                session.setStatus(AIInterviewStatus.COMPLETED);
                session.setCompletedAt(java.time.LocalDateTime.now());

                aiInterviewSessionRepository.save(session);
            }

            return new AIInterviewAnswerResponse(
                    savedAnswer.getId(),
                    savedAnswer.getQuestion().getId(),
                    savedAnswer.getCandidateAnswer(),
                    savedAnswer.getScore(),
                    savedAnswer.getCorrect(),
                    savedAnswer.getFeedback(),
                    nextQuestion != null ? nextQuestion.getId() : null,
                    nextQuestion != null ? nextQuestion.getQuestion() : null,
                    nextQuestion != null ? nextQuestion.getQuestionType() : null,
                    session.getStatus()
            );
        }
    }