package com.example.question_service.service;

import com.example.question_service.dto.AnswerQuestionRequest;
import com.example.question_service.entity.Question;
import com.example.question_service.dto.QuestionResponse;
import com.example.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        try {
            return questionRepository.findAll();
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Question> getQuestionsByCategory(String category) {
        try {
            return questionRepository.findByCategory(category);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();

    }

    public Long addQuestion(Question question) {
        questionRepository.save(question);
        return question.getId();
    }

    public List<Long> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Long> questions = questionRepository.findRandomQuestionsByCategory(categoryName, numQuestions);
        return questions;
    }

    public List<QuestionResponse> getQuestionsFromId(List<Long> questionIds) {
        List<QuestionResponse> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(Long id : questionIds) {
            questions.add(questionRepository.findById(id).get());
        }

        for(Question question : questions) {
            QuestionResponse wrapper = new QuestionResponse();
            wrapper.setId(question.getId());
            wrapper.setQuestionTitle(question.getQuestionTitle());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrapper.setOption4(question.getOption4());
            wrappers.add(wrapper);
        }

        return wrappers;
    }

    public Integer getScore(List<AnswerQuestionRequest> answerQuestionRequests) {
        int right = 0;
        for(AnswerQuestionRequest answerQuestionRequest : answerQuestionRequests) {
            Question question = questionRepository.findById(answerQuestionRequest.getId()).get();
            if(answerQuestionRequest.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }
        return right;
    }
}
