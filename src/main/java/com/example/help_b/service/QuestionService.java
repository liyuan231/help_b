package com.example.help_b.service;

import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;

import java.util.List;

public interface QuestionService {
    public void insertQuestion(Question question);

    List<QuestionDto> getQuestions(Integer page,Integer size);

    Integer sum();

    Integer sum(String userId);

    List<QuestionDto> getPersonalQuestions(int page, Integer size, String id);
}
