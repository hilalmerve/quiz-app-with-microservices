package com.example.quiz_service.service;

import com.example.quiz_service.feign.AIClient;
import com.example.quiz_service.model.dto.QuizGenerationRequest;
import com.example.quiz_service.model.dto.QuizGenerationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final AIClient aiClient;

    public QuizGenerationResponse generateQuestions(
            String category,
            Integer count
    ) {

        QuizGenerationRequest request =
                new QuizGenerationRequest();

        request.setCategory(category);
        request.setQuestionCount(count);

        return aiClient.generateQuiz(request);
    }
}
