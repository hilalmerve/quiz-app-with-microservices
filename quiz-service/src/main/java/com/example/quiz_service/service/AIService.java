package com.example.quiz_service.service;

import com.example.quiz_service.feign.AIClient;
import com.example.quiz_service.dto.GenerationQuizRequest;
import com.example.quiz_service.dto.GenerationQuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIService {

    private final AIClient aiClient;

    public GenerationQuizResponse generateQuestions(
            String category,
            Integer count
    ) {

        GenerationQuizRequest request =
                new GenerationQuizRequest();

        request.setCategory(category);
        request.setQuestionCount(count);

        return aiClient.generateQuiz(request);
    }
}
