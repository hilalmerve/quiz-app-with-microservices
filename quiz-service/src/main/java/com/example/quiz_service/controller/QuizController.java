package com.example.quiz_service.controller;

import com.example.quiz_service.dto.CreateQuizRequest;
import com.example.quiz_service.dto.QuestionResponse;
import com.example.quiz_service.dto.AnswerQuestionRequest;
import com.example.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<Long> createQuiz(@RequestBody CreateQuizRequest createQuizRequest) {
        return new ResponseEntity<>(quizService.createQuiz(createQuizRequest.getCategoryName(), createQuizRequest.getNumQuestions(), createQuizRequest.getTitle()), HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestion(@PathVariable Long id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<AnswerQuestionRequest> answerQuestionRequests) {
        return new ResponseEntity<>(quizService.calculateResult(id, answerQuestionRequests), HttpStatus.OK);
    }
}
