package com.mr.egzamator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "answer")
    @NotBlank(message = "Answer cannot be null")
    @Size(min = 1, max = 1)
    private String answer;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Question question;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Mark mark;
}
