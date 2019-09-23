package com.mr.egzamator.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TestResultDTO {
    private String userId;
    private String subject;
    private String name;
    private List<AnswerDTO> answers;
    private List<QuestionDTO> questions;
//    private MarkDTO markDTO;
}
