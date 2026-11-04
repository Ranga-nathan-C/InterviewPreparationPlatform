package com.example.interviewprep.service;


import com.example.interviewprep.dto.AIQuestionResponse;
import com.example.interviewprep.dto.AIAnswerEvaluationResponse;
import com.example.interviewprep.entity.AIDifficulty;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import com.example.interviewprep.dto.AIInterviewFeedbackResponse;

@Service
public class AIQuestionService {
    private final ChatClient chatClient;

    public AIQuestionService(ChatClient.Builder chatClientBuilder){

        this.chatClient= chatClientBuilder.build();
    }

    public AIQuestionResponse generateQuestion(String category, AIDifficulty difficulty){
        return chatClient.prompt()
                .user("""
                        Generate one interview question.

                        Category: %s
                        Difficulty: %s

                        The question should test the candidate's
                        understanding of the topic.

                        Return the response in the required
                        AIQuestionResponse structure.
                        """.formatted(category, difficulty))
                .call()
                .entity(AIQuestionResponse.class);
    }
    public AIAnswerEvaluationResponse evaluateAnswer(
            String question,
            String expectedAnswer,
            String candidateAnswer) {

        return chatClient.prompt()
                .user("""
                    Evaluate the candidate's answer to the interview question.

                    Question:
                    %s

                    Expected Answer:
                    %s

                    Candidate Answer:
                    %s

                    Evaluate the candidate's understanding.

                    Give a score from 0 to 10.
                    Mark correct as true if the answer is substantially correct,
                    otherwise false.

                    Give concise and useful feedback.

                    Return the response in the required
                    AIAnswerEvaluationResponse structure.
                    """.formatted(
                        question,
                        expectedAnswer,
                        candidateAnswer))
                .call()
                .entity(AIAnswerEvaluationResponse.class);
    }

    public AIInterviewFeedbackResponse generatePersonalizedFeedback(
            String category,
            String difficulty,
            int totalQuestions,
            int answeredQuestions,
            int totalScore,
            int maximumScore,
            int percentage,
            int correctAnswers,
            int incorrectAnswers) {

        return chatClient.prompt()
                .user("""
                    Generate personalized interview feedback for a candidate.

                    Category: %s
                    Difficulty: %s
                    Total Questions: %d
                    Answered Questions: %d
                    Total Score: %d
                    Maximum Score: %d
                    Percentage: %d
                    Correct Answers: %d
                    Incorrect Answers: %d

                    Analyze the candidate's performance.

                    Provide:
                    1. Overall feedback
                    2. Strengths
                    3. Weak areas
                    4. Recommendations for improvement

                    Return the response in the required
                    AIInterviewFeedbackResponse structure.
                    """.formatted(
                        category,
                        difficulty,
                        totalQuestions,
                        answeredQuestions,
                        totalScore,
                        maximumScore,
                        percentage,
                        correctAnswers,
                        incorrectAnswers))
                .call()
                .entity(AIInterviewFeedbackResponse.class);
    }
}
