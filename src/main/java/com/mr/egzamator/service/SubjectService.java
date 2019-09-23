package com.mr.egzamator.service;

import com.mr.egzamator.model.Subject;
import com.mr.egzamator.respository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class SubjectService {
    private SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Set<Subject> getTeacherSubjects(String userId) {
        log.info("Looking for teacher " + userId + " subjects...");
        Set<Subject> subjects = subjectRepository.findTeacherSubjectsByUserId(userId);

        if(subjects.size() > 0) {
            log.info("Subjects found: " + subjects);
            return subjects;
        }
        log.info("Subjects for teacher " + userId + " not found!");
        return null;
    }

    public Set<Subject> getStudentSubjects(String userId) {
        log.info("Looking for student " + userId + " subjects...");
        Set<Subject> subjects = subjectRepository.findStudentSubjectsByUserId(userId);

        if(subjects.size() > 0) {
            log.info("Subjects found: " + subjects);
            return subjects;
        }
        log.info("Subjects for student " + userId + " not found!");
        return null;
    }
}
