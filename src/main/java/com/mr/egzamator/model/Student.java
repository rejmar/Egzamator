package com.mr.egzamator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    private int id;

    @Column(name = "index_number")
    private String indexNumber;

    @Column(name = "password")
    private String password;
    private Set<SubjectStudent> subjectStudents;
    private Set<Mark> marks;


    public Student(String indexNumber,String password) {
        this.indexNumber = indexNumber;
        this.password = password;

    }

    public Student(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_student")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<SubjectStudent> getSubjectStudents() {
        return subjectStudents;
    }

    public void setSubjectStudents(Set<SubjectStudent> subjectStudents) {
        this.subjectStudents = subjectStudents;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
