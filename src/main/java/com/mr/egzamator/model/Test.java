package com.mr.egzamator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tests")
public class Test {

    private int id;
    private String name;
    private Subject subject;
    private Set<Question> questions;
    private Mark mark;

    public Test(String name) {
        this.name = name;
//        this.subject = subject;
//        this.subjectTests = new HashSet<>();
//        this.testMarks = new HashSet<>();
    }

    public Test(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_test")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @OneToOne(mappedBy = "test")
    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
