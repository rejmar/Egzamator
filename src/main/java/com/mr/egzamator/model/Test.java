package com.mr.egzamator.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "subjects_id_subject")
    private Subject subject;
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private Set<Question> questions;
    @OneToOne(mappedBy = "test")
    private Mark mark;
}
