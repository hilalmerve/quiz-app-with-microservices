package com.example.quiz_service.dto;

import lombok.Data;

@Data
public class CreateQuizRequest {
    String categoryName;
    Integer numQuestions;
    String title;
}
