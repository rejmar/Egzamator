package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "ANS_A")
    private String ans_a;

    @Column(name = "ANS_B")
    private String ans_b;

    @Column(name = "ANS_C")
    private String ans_c;

    @Column(name = "ANS_D")
    private String ans_d;

    @Column(name = "CORRECT_ANSWER")
    private char correct_answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;
}
