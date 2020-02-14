package com.example.help_b.dao;

import com.example.help_b.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDao {

    @Insert("insert into question(title,detail,tags,createdAt,modifiedAt,author) values(" +
            "#{title},#{detail},#{tags},#{createdAt},#{modifiedAt},#{author})")
    public void insertQuestion(Question question);
    @Select("select * from question")
    public List<Question> getQuestions(Integer number);
}
