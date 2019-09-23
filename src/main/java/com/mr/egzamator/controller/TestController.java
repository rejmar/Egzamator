package com.mr.egzamator.controller;

import com.mr.egzamator.dto.AnswerDTO;
import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.dto.TestResultDTO;
import com.mr.egzamator.model.Answer;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(value = "/getTeacherTest")
    public Test getTeacherTest(@RequestParam String userId, @RequestParam(value = "name") String testName) {
        return testService.getTeacherTest(userId, testName);
    }

    @PostMapping(value = "/getTests")
    public Set<Test> getTests(@RequestParam String userId) {
        return testService.getTests(userId);
    }

    @PostMapping(value = "/addTest")
    public Test createTest(@RequestBody TestDTO test) {
        return testService.createTest(test);
    }

    @PostMapping(value = "/addAnswers")
    public void addStudentAnswers(@RequestBody TestResultDTO testResultDTO) {
        testService.addStudentAnswers(testResultDTO);
    }

    @GetMapping(value = "/remove")
    public void removeTest(@RequestParam String testName) {
        testService.removeTest(testName);
    }
}
