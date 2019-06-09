package com.mr.egzamator.service;

import com.mr.egzamator.model.Mark;
import com.mr.egzamator.respository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService {
    private MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }


    public List<Mark> getAllMarks() {

        return markRepository.findAll();
    }
}
