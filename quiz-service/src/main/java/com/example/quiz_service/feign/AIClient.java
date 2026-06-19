package com.example.quiz_service.feign;

import com.example.quiz_service.model.dto.QuizGenerationRequest;
import com.example.quiz_service.model.dto.QuizGenerationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "ai-service",
        url = "http://ai-service:8000"
)
public interface AIClient {

    @PostMapping("/generate-quiz")
    QuizGenerationResponse generateQuiz(
            @RequestBody QuizGenerationRequest request
    );
}