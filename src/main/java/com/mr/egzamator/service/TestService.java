package com.mr.egzamator.service;

import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.dto.TestResultDTO;
import com.mr.egzamator.model.*;
import com.mr.egzamator.respository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TestService {
    private TestRepository testRepository;
    private QuestionRepository questionRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;
    private MarkRepository markRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public TestService(TestRepository testRepository, QuestionRepository questionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, MarkRepository markRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.markRepository = markRepository;
    }

    public Test getTeacherTest(String userId, String testName) {
        log.info("Looking for " + testName + " test...");
        Optional<Test> oTest = testRepository.findByUserIdAndTestName(userId, testName);

        if (oTest.isPresent()) {
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
    public Test createTest(TestDTO newTest) {
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
        Set<Test> tests = testRepository.findTeacherTestsByUserId(userId);
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
        if (questions.size() > 0) {
            log.info("Quetions for test " + testName + " found" + questions);
            return questions;
        }
        log.info("Questions for test " + testName + " not found!");
        return null;
    }

    @Transactional
    public void addStudentAnswers(TestResultDTO testResultDTO) {
        System.out.println(testResultDTO);
//        log.info("Looking for questions for test " + testName);
        Optional<Student> oStudent = studentRepository.findByUserId(testResultDTO.getUserId());
//
        if (oStudent.isPresent()) {
            Student student = oStudent.get();
            Mark mark = new Mark();
            mark.setStudent(student);
            System.out.println(testResultDTO.getSubject());
            Optional<Test> test = testRepository.findByName(testResultDTO.getName());
            if (test.isPresent()) {
                System.out.println(test.get().getTeacher());
                mark.setTeacher(test.get().getTeacher());
                mark.setTest(test.get());
            }
             em.persist(mark);

            List<Answer> answers = new ArrayList<>();
            List<Question> questions = new ArrayList<>();

            testResultDTO.getAnswers().forEach(answerDTO -> {
                Answer answer = new Answer();
                answer.setAnswer(answerDTO.getAnswer());

                Optional<Question> oQuestion = questionRepository.findById(answerDTO.getId());
                if (oQuestion.isPresent()) {
                    answer.setQuestion(oQuestion.get());
                    questions.add(oQuestion.get());
                }


                answer.setMark(mark);
                answers.add(answer);
            });

            mark.setAnswers(answers);

            int counter = 0;
            for(Question q: questions) {
                for( Answer a: answers) {
                    if(q.getCorrect_ans().equals(a.getAnswer())){
                        counter++;
                    }
                }
            }
//            questions.forEach(question -> {
//                answers.forEach(answer -> {
//                    if (question.getCorrect_ans().equals(answer.getAnswer())) {
//                        counter.getAndIncrement();
//                    }
//                });
//            });
            System.out.println(counter);
            System.out.println(questions.size());
            double markValue = (double) (counter / questions.size()*5) ;
            System.out.println(markValue);


            mark.setMark(markValue);

            System.out.println(mark);
            markRepository.save(mark);

//            return markRepository.save(mark);
        }

//        Optional<Question> oQuestion = questionRepository.findById(answerDTO.getQuestionId());
//
//        if (oQuestion.isPresent()) {
//            answer.setQuestion(oQuestion.get());
//            answer.setAnswer(answerDTO.getAnswer());
//
//            TestResultDTO testResultDTO = new TestResultDTO();
//            testResultDTO.
//            Mark mark = new Mark();
//            mark.set
//            answer.setMark();
//        return null;

    }

    public void removeTest(String testName) {
        log.info("Looking for " + testName + " to delete...");
        Optional<Test> oTest = testRepository.findByName(testName);
        oTest.ifPresent(test -> {
            log.info("Test " + testName + " found. Trying to delete...");
            testRepository.delete(test);
            log.info("Test " + testName + " deleted!");
        });
    }
}

