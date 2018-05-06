package com.mr.egzamator.model;

import javax.persistence.*;

@Entity
@Table(name = "subjects_teachers")
public class SubjectTeacher {

    private int id;
    private Teacher teacher;
    private Subject subject;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subjects_teachers")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "teachers_id_teacher")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
