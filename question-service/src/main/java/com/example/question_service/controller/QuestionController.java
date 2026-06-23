package com.example.question_service.controller;

import com.example.question_service.entity.Question;
import com.example.question_service.dto.QuestionResponse;
import com.example.question_service.dto.AnswerQuestionRequest;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Long> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Long>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        return new ResponseEntity<>(questionService.getQuestionsForQuiz(categoryName, numQuestions), HttpStatus.OK);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsFromId(@RequestBody List<Long> questionIds) {

        return new ResponseEntity<>(questionService.getQuestionsFromId(questionIds), HttpStatus.OK);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<AnswerQuestionRequest> answerQuestionRequests) {
        return new ResponseEntity<>(questionService.getScore(answerQuestionRequests), HttpStatus.OK);
    }
}
