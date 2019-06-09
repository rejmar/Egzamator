package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    private int id;

    @Column(name = "index_number")
    private String indexNumber;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<SubjectStudent> subjectStudents;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Mark> marks;
}
