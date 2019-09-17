package com.mr.egzamator.service;

import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Subject;
import com.mr.egzamator.model.Teacher;
import com.mr.egzamator.respository.SubjectRepository;
import com.mr.egzamator.respository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class TeacherService {
    private TeacherRepository teacherRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
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
                log.info("Teacher with userId=" + userId + " not found");
            } else {
                log.info("Subject " + subjectName + " not found");
            }
        }
    }

    public Set<Subject> getSubjects(String userId){
        log.info("Looking for user " + userId);
        Optional<Teacher> oTeacher = teacherRepository.findByUserId(userId);
            return oTeacher.map(Teacher::getSubjects).orElse(null);
    }
}
