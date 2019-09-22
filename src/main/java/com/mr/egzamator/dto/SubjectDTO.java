package com.mr.egzamator.dto;

import com.mr.egzamator.model.Test;
import lombok.Data;

import java.util.Set;

@Data
public class SubjectDTO {
    private Long id;
    private String name;
    private Set<Test> tests;
}
