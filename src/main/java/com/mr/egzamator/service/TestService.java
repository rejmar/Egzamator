package com.mr.egzamator.service;

import com.mr.egzamator.dto.TestDTO;
import com.mr.egzamator.model.Test;
import com.mr.egzamator.respository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TestService {
    private TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test getTest(Long testId) {
        log.info("Looking for " + testId + " test...");
        Optional<Test> oTest = testRepository.findById(Math.toIntExact(testId));

        if(oTest.isPresent()) {
            log.info("Test found");
            return oTest.get();
        } else {
            log.info("Test " + testId + " not found!");
            return null;
        }
    }

    public Test createTest (TestDTO newTest) {
        return null;
    }
}
