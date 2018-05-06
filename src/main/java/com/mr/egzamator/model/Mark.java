package com.mr.egzamator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "marks")
public class Mark {

    private int id;
    private double mark;
    private Test test;
    private Student student;


//    public Mark(double mark) {
//        this.mark = mark;
//
//    }

    public Mark(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mark")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "mark")
    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @OneToOne
    @JoinColumn(name = "tests_id_test")
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @ManyToOne
    @JoinColumn(name = "students_id_student")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
