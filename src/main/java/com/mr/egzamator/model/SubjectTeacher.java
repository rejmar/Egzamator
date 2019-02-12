package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "subjects_teachers")
public class SubjectTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_subjects_teachers")
    private int id;

    @ManyToOne
    @JoinColumn(name = "teachers_id_teacher")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    private Subject subject;
}
