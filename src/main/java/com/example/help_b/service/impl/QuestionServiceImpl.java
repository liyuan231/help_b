package com.example.help_b.service.impl;

import com.example.help_b.dao.QuestionDao;
import com.example.help_b.model.BasicUser;
import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;
import com.example.help_b.service.QuestionService;
import com.example.help_b.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource(name = "questionDao")
    QuestionDao questionDao;
    @Resource(name = "userServiceImpl")
    UserService userService;
    @Override
    public void insertQuestion(Question question) {
        questionDao.insertQuestion(question);
    }

    @Override
    public List<QuestionDto> selectQuestions(Integer page, Integer size) {
        List<Question> questions=questionDao.selectQuestions((page-1)*size,size);
        List<QuestionDto> questionDtos=new ArrayList<>();
        for(Question question:questions){
//            System.out.println((int)question.getAuthor());
            BasicUser basicUser= userService.selectGitHubUserById((int) question.getAuthor());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setBasicUser(basicUser);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    @Override
    public Integer sum() {
        return questionDao.sum();
    }

    @Override
    public Integer sum(String userId) {
        return questionDao.personalQuestionsSum(userId);
    }

    @Override
    public List<QuestionDto> selectPersonalQuestions(int page, Integer size, String userId) {
        List<Question> questions=questionDao.selectPersonalQuestions((page-1)*size,size,userId);
        List<QuestionDto> questionDtos=new ArrayList<>();
        for(Question question:questions){
//            System.out.println((int)question.getAuthor());
            BasicUser basicUser= userService.selectGitHubUserById((int) question.getAuthor());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setBasicUser(basicUser);
            questionDtos.add(questionDto);
        }
        return questionDtos;
    }

    @Override
    public QuestionDto selectQuestionById(Integer questionId) {
        Question question = questionDao.selectQuestionById(questionId);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        questionDto.setBasicUser(userService.selectGitHubUserById((int) questionDto.getAuthor()));
        return questionDto;
    }

    @Override
    public void updateQuestion(Question question) {
        questionDao.updateQuestion(question);
    }
}
