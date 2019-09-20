package com.mr.egzamator.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private String description;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String correctAns;
}
