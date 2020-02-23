package com.example.help_b.service;

import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;

import java.util.List;

public interface QuestionService {
    public void insertQuestion(Question question);

    List<QuestionDto> selectQuestions(Integer page, Integer size);

    Integer sum();

    Integer sum(String userId);

    List<QuestionDto> selectPersonalQuestions(int page, Integer size, String id);

    QuestionDto selectQuestionById(Integer questionId);

    void updateQuestion(Question question);
}
