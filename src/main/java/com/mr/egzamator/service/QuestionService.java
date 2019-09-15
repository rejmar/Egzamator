package com.mr.egzamator.service;

import com.mr.egzamator.model.Question;
import com.mr.egzamator.respository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> getTestQuestions(String testName) {
        return questionRepository.getQuestions(testName);
    }

}
