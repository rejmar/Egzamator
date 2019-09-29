package com.mr.egzamator.controller;

import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Subject;
import com.mr.egzamator.service.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/getTeacherSubjects")
    public Set<Subject> getTeacherSubjects(@RequestParam String userId) {
        try {
            return subjectService.getTeacherSubjects(userId);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/getStudentSubjects")
    public Set<Subject> getStudentSubjects(@RequestParam String userId) {
        try {
            return subjectService.getStudentSubjects(userId);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
