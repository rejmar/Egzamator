package com.mr.egzamator.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<User> users;
}