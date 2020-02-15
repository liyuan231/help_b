package com.example.help_b.dao;

import com.example.help_b.model.Question;
import com.example.help_b.model.QuestionDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionDao {

    @Insert("insert into question(title,detail,tags,createdAt,modifiedAt,author) values(" +
            "#{title},#{detail},#{tags},#{createdAt},#{modifiedAt},#{author})")
    public void insertQuestion(Question question);
    @Select("select * from question limit #{size} offset #{offset}")
    public List<Question> getQuestions(@Param("offset") Integer offset,
                                       @Param("size") Integer size);

    @Select("select count(*) from question")
    Integer sum();

    @Select("select count(*) from question where author = #{userId}")
    Integer personalQuestionsSum(@Param("userId") String userId);

    @Select("select * from question where author=#{id} limit #{size} offset #{offset}")
    List<Question> getPersonalQuestions(int offset, Integer size, String id);

    @Select("select * from question where id=#{id}")
    Question getQuestionById(@Param("id") Integer questionId);
}
