package com.mr.egzamator.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TestDTO {
    private String name;
    private LocalDate date;
    private Long duration;
    private List<QuestionDTO> questions;
}
