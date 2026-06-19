package com.example.quiz_service.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizGenerationResponse {

    private String category;
    private Integer questionCount;
    private List<GeneratedQuestion> questions;

    // getter setter
}
