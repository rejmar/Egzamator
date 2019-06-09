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
    @OneToOne
    @JoinColumn(name = "tests_id_test")
    private Test test;
    @ManyToOne
    @JoinColumn(name = "students_id_student")
    private Student student;
}
