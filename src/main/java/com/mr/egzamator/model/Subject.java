package com.mr.egzamator.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subjects")
public class Subject {

    private int id;
    private String name;
    private Set<SubjectTeacher> subjectTeachers;
    private Set<SubjectStudent> subjectStudents;
    private Set<Test> tests;

    public Subject(){

    }

    public Subject(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subject")
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

    @OneToMany(mappedBy = "subject")
    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<SubjectStudent> getSubjectStudents() {
        return subjectStudents;
    }

    public void setSubjectStudents(Set<SubjectStudent> subjectStudents) {
        this.subjectStudents = subjectStudents;
    }


    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL)
    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
