package com.mr.egzamator.service;

import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.model.Question;
import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.respository.QuestionRepository;
import com.mr.egzamator.respository.SubjectRepository;
import com.mr.egzamator.respository.TeacherRepository;
import com.mr.egzamator.respository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class TestService {
    private TestRepository testRepository;
    private QuestionRepository questionRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public TestService(TestRepository testRepository, QuestionRepository questionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    public Test getTeacherTest(String userId, String testName) {
        log.info("Looking for " + testName + " test...");
        Optional<Test> oTest = testRepository.findByUserIdAndTestName(userId, testName);

        if(oTest.isPresent()) {
            log.info("Test found");
            Test foundTest = oTest.get();
            foundTest.setQuestions(new HashSet<>(getTestQuestions(testName)));
            return foundTest;
        } else {
            log.info("Test " + testName + " not found for user " + userId + "!");
            return null;
        }
    }

    @Transactional
    public Test createTest (TestDTO newTest) {
        log.info("Looking for test " + newTest.getName());

        Optional<Test> oTest = testRepository.findByUserIdAndTestName(newTest.getUserId(), newTest.getName());
        if (oTest.isPresent()) {
            log.info("Test " + oTest.get().getName() + " will be updated");
        } else {
            log.info("Creating new test " + newTest.getName());
        }
        Test test = oTest.orElseGet(Test::new);
        Timestamp ts = new Timestamp(newTest.getDate().getTime());
        test.setDate(ts);
        test.setDuration(newTest.getDuration());
        test.setName(newTest.getName());

        Set<Question> questions = new HashSet<>();
        newTest.getQuestions().forEach(questionDTO -> {
            log.info("Looking for question " + questionDTO.getId());
            Optional<Question> oQuestion = questionRepository.findByTestNameAndId(newTest.getName(), questionDTO.getId());
            if (oQuestion.isPresent()) {
                log.info("Question " + questionDTO.getId() + " will be updated");
            } else {
                log.info("Creating new question " + questionDTO.getId());
            }
            Question question = oQuestion.orElseGet(Question::new);

            question.setDescription(questionDTO.getDescription());
            question.setAns_a(questionDTO.getAns_a());
            question.setAns_b(questionDTO.getAns_b());
            question.setAns_c(questionDTO.getAns_c());
            question.setAns_d(questionDTO.getAns_d());
            question.setCorrect_ans(questionDTO.getCorrect_ans());
            question.setTest(test);
            questions.add(question);
        });
        test.setQuestions(questions);
        Optional<Subject> subject = subjectRepository.findByName(newTest.getSubject());

        test.setSubject(subject.orElse(null));
        Optional<Teacher> teacher = teacherRepository.findByUserId(newTest.getUserId());
        test.setTeacher(teacher.orElse(null));

        log.info("Saving new test " + test.getName());
        testRepository.save(test);
        log.info("Test saved " + test);
    return test;
    }

    public Set<Test> getTests(String userId) {
        log.info("Looking for " + userId + " tests...");
        Set<Test> tests = testRepository.findTestsByUserId(userId);
//        tests.forEach(test -> test.get);
        if (tests.size() > 0) {
            log.info("Tests found: " + tests);
            return tests;
        }
        log.info("Tests for " + userId + " not found!");
        return null;
    }

    public Set<Question> getTestQuestions(String testName) {
        log.info("Looking for questions for test " + testName);
        Set<Question> questions = questionRepository.getQuestions(testName);
        if( questions.size() > 0 ) {
            log.info("Quetions for test " + testName + " found" + questions);
            return questions;
        }
        log.info("Questions for test " + testName + " not found!");
        return null;
    }
}
