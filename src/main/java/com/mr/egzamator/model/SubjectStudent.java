package com.mr.egzamator.model;

import javax.persistence.*;

@Entity
@Table(name = "subjects_students")
public class SubjectStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subjects_students")
    private int id;
    @ManyToOne
    @JoinColumn(name = "students_id_student")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    private Subject subject;
}
