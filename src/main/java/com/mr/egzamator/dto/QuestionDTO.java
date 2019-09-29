package com.mr.egzamator.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String description;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String correct_ans;
}
