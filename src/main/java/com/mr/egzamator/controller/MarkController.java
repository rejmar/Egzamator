package com.mr.egzamator.controller;

import com.mr.egzamator.model.Mark;
import com.mr.egzamator.service.MarkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/mark")
public class MarkController {
    private static final Logger LOGGER = LogManager.getLogger(MarkController.class);

    private MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping(path = "/getAllMarks")
    public @ResponseBody List<Mark> getAllMarks(){
        LOGGER.info("getAllMarks called");
        return markService.getAllMarks();
    }
}
