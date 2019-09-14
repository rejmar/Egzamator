package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "marks")
@NoArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MARK")
    private int id;

    @Column(name = "MARK")
    private double mark;
    @OneToOne
    @JoinColumn(name = "TESTS_ID_TEST")
    private Test test;
    @ManyToOne
    @JoinColumn(name = "STUDENTS_ID_STUDENT")
    private Student student;
}
