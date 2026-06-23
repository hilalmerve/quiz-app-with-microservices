package com.example.quiz_service.controller;

import com.example.quiz_service.feign.AIClient;
import com.example.quiz_service.dto.GenerationQuizRequest;
import com.example.quiz_service.dto.GenerationQuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AITestController {

    private final AIClient aiClient;

    @GetMapping("/test-ai")
    public GenerationQuizResponse testAI() {

        GenerationQuizRequest request =
                new GenerationQuizRequest();

        request.setCategory("Java");
        request.setQuestionCount(3);

        return aiClient.generateQuiz(request);
    }
}