package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_question")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "ans_a")
    private String ans_a;

    @Column(name = "ans_b")
    private String ans_b;

    @Column(name = "ans_c")
    private String ans_c;

    @Column(name = "ans_d")
    private String ans_d;

    @Column(name = "correct_answer")
    private char correct_answer;

    @ManyToOne
    @JoinColumn(name = "tests_id_test")
    private Test test;
}
