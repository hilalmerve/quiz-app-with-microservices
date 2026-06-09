package com.example.quiz_service.model.dto;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    Integer numQuestions;
    String title;
}
