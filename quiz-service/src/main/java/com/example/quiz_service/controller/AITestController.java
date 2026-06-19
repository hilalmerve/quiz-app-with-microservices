package com.example.quiz_service.controller;

import com.example.quiz_service.feign.AIClient;
import com.example.quiz_service.model.dto.QuizGenerationRequest;
import com.example.quiz_service.model.dto.QuizGenerationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AITestController {

    private final AIClient aiClient;

    @GetMapping("/test-ai")
    public QuizGenerationResponse testAI() {

        QuizGenerationRequest request =
                new QuizGenerationRequest();

        request.setCategory("Java");
        request.setQuestionCount(3);

        return aiClient.generateQuiz(request);
    }
}