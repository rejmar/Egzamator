package com.mr.egzamator.controller;

import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping
    public Test getTest(@RequestParam Long testId) {
        return testService.getTest(testId);
    }
    
    @PostMapping
    public Test createTest(@RequestBody TestDTO test) {
        return testService.createTest(test);
    }
}
