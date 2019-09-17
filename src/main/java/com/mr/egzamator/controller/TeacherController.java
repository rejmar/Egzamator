package com.mr.egzamator.controller;

import com.mr.egzamator.model.Subject;
import com.mr.egzamator.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/subjects")
    public Set<Subject> getSubjects(@RequestParam String userId) {
        Set<Subject> subjects = null;
        subjects = teacherService.getSubjects(userId);
        return subjects;
    }

    @GetMapping("/assignSubject")
    public void assignSubject(@RequestParam String userId, @RequestParam String subjectName) {
        teacherService.assignToSubject(userId, subjectName);
    }
}
