package com.example.quiz_service.model.dto;

import lombok.Data;

@Data
public class GeneratedQuestion {

    private String questionTitle;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;
}
