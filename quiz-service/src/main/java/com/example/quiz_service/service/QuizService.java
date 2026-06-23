package com.example.quiz_service.service;

import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.dto.QuestionResponse;
import com.example.quiz_service.entity.Quiz;
import com.example.quiz_service.dto.AnswerQuestionRequest;
import com.example.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<QuestionResponse> getQuizQuestions(Long id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Long> questionIds = quiz.getQuestionIds();
        List<QuestionResponse> questions = quizInterface.getQuestionsFromId(questionIds);

        return questions;
    }

    public Integer calculateResult(Integer id, List<AnswerQuestionRequest> answerQuestionRequests) {
        Integer score = quizInterface.getScore(answerQuestionRequests);
        return score;
    }
}
