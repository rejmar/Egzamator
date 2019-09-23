package com.mr.egzamator.service;

import com.mr.egzamator.dto.TeacherTestDTO;
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

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
public class TeacherService {
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;
    private TestRepository testRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository, TestRepository testRepository, QuestionRepository questionRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }

    @Transactional
    public void assignToSubject(String userId, String subjectName) {
        log.info("Looking for user " + userId + " and subject " + subjectName);
        Optional<Subject> oSubject = subjectRepository.findByName(subjectName);
        Optional<Teacher> oTeacher = teacherRepository.findByUserId(userId);

        if (oSubject.isPresent() && oTeacher.isPresent()){
            log.info("User and subject found");
            Teacher teacher = oTeacher.get();
            Subject subject = oSubject.get();

            Set<Subject> subjects = teacher.getSubjects();
            if(!subjects.contains(subject)) {
                subjects.add(subject);
                teacherRepository.saveAndFlush(teacher);
                log.info(subject.getName() + " added to teacher's " + teacher.getUser().getIndexNumber() + " subjects");
            } else {
                log.info(teacher.getUser().getIndexNumber() + " already assigned to " + subject.getName());
            }
        } else {
            if (!oTeacher.isPresent()) {
                log.info("Teacher with userId " + userId + " not found");
            } else {
                log.info("Subject " + subjectName + " not found");
            }
        }
    }

    public Map<String, Set<TeacherTestDTO>> getTests(String userId){
        log.info("Looking for user " + userId);
        Set<Subject> s = subjectRepository.findTeacherSubjectsTestsByUserId(userId);

        System.out.println(s);
        Set<Test> t = testRepository.findTeacherTestsByUserId(userId);

        Map<String, Set<TeacherTestDTO>> subjectTestMap = new HashMap<>();
        s.forEach(subject -> {
            Set<TeacherTestDTO> teacherTestDTOs = new HashSet<>();

            t.forEach(test -> {
                if (test.getSubject().getName().equals(subject.getName())) {
                    TeacherTestDTO teacherTestDTO = new TeacherTestDTO();
                    Set<Question> questions = questionRepository.getQuestions(test.getName());
                    teacherTestDTO.setId(test.getId());
                    teacherTestDTO.setName(test.getName());
                    teacherTestDTO.setDate(test.getDate());
                    teacherTestDTO.setDuration(test.getDuration());
                    teacherTestDTO.setQuestions(questions);
                    teacherTestDTOs.add(teacherTestDTO);
                }
            });
            subjectTestMap.put(subject.getName(),teacherTestDTOs);
        });

        System.out.println(subjectTestMap);
//        System.out.println(tests);

//        Optional<Teacher> oTeacher = teacherRepository.findByUserId(userId);
//        if (oTeacher.isPresent()) {
//            Set<SubjectDTO> subjects = new HashSet<>();
//            oTeacher.get().getSubjects().forEach(subject -> {
//                SubjectDTO subjectDTO = new SubjectDTO();
//                subjectDTO.setId(subject.getId());
//                subjectDTO.setName(subject.getName());
//                subjectDTO.setTests(subject.getTests());
//                subjects.add(subjectDTO);
//            });


            return subjectTestMap;
//        } else {
//            return null;
//        }

    }
}
