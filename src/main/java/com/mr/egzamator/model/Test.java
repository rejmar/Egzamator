package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_test")
    private int id;

    @Column(name = "name")
    private String name;
    private Subject subject;
    private Set<Question> questions;
    private Mark mark;


    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    public Subject getSubject() {
        return subject;
    }

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    public Set<Question> getQuestions() {
        return questions;
    }

    @OneToOne(mappedBy = "test")
    public Mark getMark() {
        return mark;
    }
}
