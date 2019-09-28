package com.mr.egzamator.controller;

import com.mr.egzamator.dto.TeacherTestDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Mark;
import com.mr.egzamator.model.Student;
import com.mr.egzamator.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/tests")
    public Map<String, Set<TeacherTestDTO>> getTests(@RequestParam String userId) {
        return teacherService.getTests(userId);
    }

    @GetMapping("/assignSubject")
    public void assignSubject(@RequestParam String userId, @RequestParam String subjectName) {
        try {
            teacherService.assignToSubject(userId, subjectName);
        } catch (EgzamatorException e) {
            log.error(e.getMessage());
        }
    }

    @PostMapping("/getStudentsMarks")
    public Set<Student> getStudentsMarks(@RequestParam String subjectName) {
        return teacherService.getStudentMarks(subjectName);
    }
}
