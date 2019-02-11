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
    private Set<SubjectTeacher> subjectTeachers;
    private Set<SubjectStudent> subjectStudents;
    private Set<Test> tests;


    @OneToMany(mappedBy = "subject")
    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<SubjectStudent> getSubjectStudents() {
        return subjectStudents;
    }

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    public Set<Test> getTests() {
        return tests;
    }
}
