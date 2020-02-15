package com.example.help_b;

import com.example.help_b.model.Question;
import com.example.help_b.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class HelpBApplicationTests {
    @Resource(name = "simpleDateFormat")
    SimpleDateFormat simpleDateFormat;

    @Resource(name = "questionServiceImpl")
    QuestionService questionService;
    @Test
    void contextLoads() {
        for(int i=1;i<=100;i++){
            questionService.insertQuestion(new Question("test"+i,"test"+i,"test"+i,simpleDateFormat.format(new Date()), simpleDateFormat.format(new Date()),50519854,0,0,0));
        }
    }

}
