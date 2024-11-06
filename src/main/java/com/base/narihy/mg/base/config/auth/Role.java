package com.base.narihy.mg.base.config.auth;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Association Many-to-Many avec l'entité User
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
