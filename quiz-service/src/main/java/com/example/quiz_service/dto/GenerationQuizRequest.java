package com.example.quiz_service.dto;

import lombok.Data;

@Data
public class GenerationQuizRequest {
    private String category;
    private Integer questionCount;
}
