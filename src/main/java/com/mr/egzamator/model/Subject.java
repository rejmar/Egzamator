package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subject")
    private int id;

    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "subject")
    private Set<SubjectTeacher> subjectTeachers;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<SubjectStudent> subjectStudents;
    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    private Set<Test> tests;
}
