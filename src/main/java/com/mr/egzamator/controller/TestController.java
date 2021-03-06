package com.mr.egzamator.controller;

import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.dto.TestResultDTO;
import com.mr.egzamator.exception.EgzamatorException;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(value = "/getTeacherTest")
    public Test getTeacherTest(@RequestParam String userId, @RequestParam(value = "name") String testName) {
        try {
            return testService.getTeacherTest(userId, testName);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/getTests")
    public Set<Test> getTests(@RequestParam String userId) {
        try {
            return testService.getTests(userId);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping(value = "/addTest")
    public Test createTest(@RequestBody TestDTO test) {
        try {
            return testService.createTest(test);
        } catch (EgzamatorException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
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
