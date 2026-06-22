package com.example.quiz_service.service;

import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
import com.example.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;

    public Long createQuiz(String category, int numQ, String title) {

        List<Long> questions = quizInterface.getQuestionsForQuiz(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);

        quizRepository.save(quiz);
        return quiz.getId();
    }

    public List<QuestionWrapper> getQuizQuestions(Long id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Long> questionIds = quiz.getQuestionIds();
        List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Integer score = quizInterface.getScore(responses);
        return score;
    }
}
