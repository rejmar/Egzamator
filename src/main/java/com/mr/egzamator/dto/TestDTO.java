package com.mr.egzamator.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class TestDTO {
    private String userId;
    private String subject;
    private String name;
    private Date date;
    private Date previousDate;
    private Long duration;
    private List<QuestionDTO> questions;
}
