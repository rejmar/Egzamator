package com.mr.egzamator.service;

import com.mr.egzamator.dto.StudentTestDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.*;
import com.mr.egzamator.respository.QuestionRepository;
import com.mr.egzamator.respository.StudentRepository;
import com.mr.egzamator.respository.SubjectRepository;
import com.mr.egzamator.respository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class StudentService {
    private StudentRepository studentRepository;
    private TestRepository testRepository;
    private QuestionRepository questionRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, TestRepository testRepository, QuestionRepository questionRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
        this.subjectRepository = subjectRepository;
    }

    public Set<Subject> getSubjects(String userId) throws EgzamatorException {
        log.info("Looking for user " + userId);
        Optional<Student> oTeacher = studentRepository.findByUserId(userId);
        return oTeacher.map(Student::getSubjects).orElseThrow(() -> new EgzamatorException(userId + " user not found"));
    }


    public Map<String, Set<StudentTestDTO>> getTests(String userId){
        log.info("Looking for user " + userId);
        Set<Subject> s = subjectRepository.findStudentSubjectsTestsByUserId(userId);

        Set<Test> t = testRepository.findStudentTestsByUserId(userId);

        return getStringSetMap(s, t);
    }

    public Map<String, Set<StudentTestDTO>> getSolvedTests(String userId){
        log.info("Looking for user " + userId);
        Set<Subject> s = subjectRepository.findStudentSubjectsTestsByUserId(userId);

        Set<Test> t = testRepository.findSolvedStudentTestsByUserId(userId);

        return getStringSetMap(s, t);
    }

    private Map<String, Set<StudentTestDTO>> getStringSetMap(Set<Subject> s, Set<Test> t) {
        Map<String, Set<StudentTestDTO>> subjectTestMap = new HashMap<>();
        s.forEach(subject -> {
            Set<StudentTestDTO> studentTestDTOs = new HashSet<>();

            t.forEach(test -> {
                if (test.getSubject().getName().equals(subject.getName())) {
                    StudentTestDTO studentTestDTO = new StudentTestDTO();
                    Set<Question> questions = questionRepository.getQuestions(test.getName());
                    questions.forEach(question -> question.setCorrect_ans(null));
                    studentTestDTO.setId(test.getId());
                    studentTestDTO.setName(test.getName());
                    studentTestDTO.setDate(test.getDate());
                    studentTestDTO.setDuration(test.getDuration());
                    studentTestDTO.setQuestions(questions);
                    studentTestDTOs.add(studentTestDTO);
                }
            });
            subjectTestMap.put(subject.getName(),studentTestDTOs);
        });
        return subjectTestMap;
    }
}
