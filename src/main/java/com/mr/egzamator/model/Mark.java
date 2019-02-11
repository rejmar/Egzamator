package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "marks")
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_mark")
    private int id;

    @Column(name = "mark")
    private double mark;
    private Test test;
    private Student student;


    @OneToOne
    @JoinColumn(name = "tests_id_test")
    public Test getTest() {
        return test;
    }

    @ManyToOne
    @JoinColumn(name = "students_id_student")
    public Student getStudent() {
        return student;
    }
}
