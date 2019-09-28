package com.mr.egzamator.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    @NotBlank(message = "Question Description cannot be blank")
    @Size(min = 5, max = 1000,message = "Question Description should contain 5-1000 characters")
    private String description;

    @Column(name = "ans_a")
    @NotBlank(message = "Answer A cannot be blank")
    @Size(min=1, max = 100, message = "Answer A should contain 1-100 characters")
    private String ans_a;

    @Column(name = "ans_b")
    @NotBlank(message = "Answer B cannot be blank")
    @Size(min=1, max = 100, message = "Answer B should contain 1-100 characters")
    private String ans_b;

    @Column(name = "ans_c")
    @NotBlank(message = "Answer C cannot be blank")
    @Size(min=1, max = 100, message = "Answer C should contain 1-100 characters")
    private String ans_c;

    @Column(name = "ans_d")
    @NotBlank(message = "Answer D cannot be blank")
    @Size(min=1, max = 100, message = "Answer D should contain 1-100 characters")
    private String ans_d;

    @Column(name = "correct_ans")
    @NotBlank(message = "Correct Answer cannot be empty")
    @Size(min=1, max=1, message = "Correct Answer should have 1 character")
    private String correct_ans;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    @JsonIgnore
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answer;
}
