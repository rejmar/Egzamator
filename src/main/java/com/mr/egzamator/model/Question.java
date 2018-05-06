package com.mr.egzamator.model;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    private int id;
    private String content;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private char correct_answer;
    private Test test;

//    public Question(String content, String ans_a, String ans_b, String ans_c, String ans_d, char correct_answer, Test test) {
//        this.content = content;
//        this.ans_a = ans_a;
//        this.ans_b = ans_b;
//        this.ans_c = ans_c;
//        this.ans_d = ans_d;
//        this.correct_answer = correct_answer;
//        this.test = test;
//    }

    public Question(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_question")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "ans_a")
    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    @Column(name = "ans_b")
    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    @Column(name = "ans_c")
    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    @Column(name = "ans_d")
    public String getAns_d() {
        return ans_d;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    @Column(name = "correct_answer")
    public char getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(char correct_answer) {
        this.correct_answer = correct_answer;
    }

    @ManyToOne
    @JoinColumn(name = "tests_id_test")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
