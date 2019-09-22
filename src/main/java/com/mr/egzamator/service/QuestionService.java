package com.mr.egzamator.service;

import com.mr.egzamator.model.Question;
import com.mr.egzamator.respository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
}
