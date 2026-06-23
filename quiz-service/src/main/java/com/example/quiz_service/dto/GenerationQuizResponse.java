package com.example.quiz_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenerationQuizResponse {

    private String category;
    private Integer questionCount;
    private List<GeneratedQuestion> questions;
}
