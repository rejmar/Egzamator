package com.mr.egzamator.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher {


    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    private Set<SubjectTeacher> subjectTeachers;
    public Teacher(){}

    public Teacher(String name,String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
//        this.subjectTeachers = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_teacher")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }

    public void setSubjectTeachers(Set<SubjectTeacher> subjectTeachers) {
        this.subjectTeachers = subjectTeachers;
    }
}