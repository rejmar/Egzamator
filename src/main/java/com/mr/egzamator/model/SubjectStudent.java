package com.mr.egzamator.model;

import javax.persistence.*;

@Entity
@Table(name = "subjects_students")
public class SubjectStudent {

    private int id;
    private Student student;
    private Subject subject;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subjects_students")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "students_id_student")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
