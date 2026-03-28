package com.gaxeris.templates.security.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "roles_model", name = "role")
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

}
