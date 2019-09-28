package com.mr.egzamator.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestResultDTO {
    private String userId;
    private String subject;
    private String name;
    private List<AnswerDTO> answers;
    private List<QuestionDTO> questions;
}
