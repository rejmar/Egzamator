package com.mr.egzamator.controller;

import com.mr.egzamator.dto.StudentTestDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Subject;
import com.mr.egzamator.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/subjects")
    public Set<Subject> getSubjects(@RequestParam String userId) {
        try {
            return studentService.getSubjects(userId);
        } catch (EgzamatorException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @PostMapping("/tests")
    public Map<String, Set<StudentTestDTO>> getTests(@RequestParam String userId) {
        return studentService.getTests(userId);
    }

    @PostMapping("/solvedTests")
    public Map<String, Set<StudentTestDTO>> getSolvedTests(@RequestParam String userId) {
        return studentService.getSolvedTests(userId);
    }
}
