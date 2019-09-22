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
        log.info("Looking for user " + userId + " subjects...");
        Set<Subject> subjects = subjectRepository.findSubjectsByUserId(userId);

        if(subjects.size() > 0) {
            log.info("Subjects found: " + subjects);
            return subjects;
        }
        log.info("Subjects for user " + userId + " not found!");
        return null;
    }
}
