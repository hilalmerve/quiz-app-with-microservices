package com.example.quiz_service.model.dto;

import lombok.Data;

@Data
public class QuizGenerationRequest {
    private String category;
    private Integer questionCount;
}
