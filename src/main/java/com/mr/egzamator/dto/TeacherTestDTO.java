package com.mr.egzamator.dto;

import com.mr.egzamator.model.Question;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class TeacherTestDTO {
    private Long id;
    private String name;
    private Timestamp date;
    private Long duration;
    private Set<Question> questions;
}
