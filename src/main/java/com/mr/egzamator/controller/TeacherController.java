package com.mr.egzamator.controller;

import com.mr.egzamator.dto.TeacherTestDTO;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/teacher")
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
        teacherService.assignToSubject(userId, subjectName);
    }
}
