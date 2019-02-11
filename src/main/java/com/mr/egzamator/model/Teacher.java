package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_teacher")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    private Set<SubjectTeacher> subjectTeachers;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL,orphanRemoval = true)
    public Set<SubjectTeacher> getSubjectTeachers() {
        return subjectTeachers;
    }
}