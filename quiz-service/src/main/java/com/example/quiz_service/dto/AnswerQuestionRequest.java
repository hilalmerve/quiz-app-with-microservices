package com.example.quiz_service.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerQuestionRequest {
    private Long id;
    private String response;
}
