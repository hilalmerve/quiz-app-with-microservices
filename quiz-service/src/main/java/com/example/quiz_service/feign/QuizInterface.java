package com.example.quiz_service.feign;

import com.example.quiz_service.dto.QuestionResponse;
import com.example.quiz_service.dto.AnswerQuestionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("question-service")
public interface QuizInterface {
    @GetMapping("question/generate")
    public List<Long> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions);

    @PostMapping("question/getQuestions")
    public List<QuestionResponse> getQuestionsFromId(@RequestBody List<Long> questionIds);

    @PostMapping("question/getScore")
    public Integer getScore(@RequestBody List<AnswerQuestionRequest> answerQuestionRequests);

}
