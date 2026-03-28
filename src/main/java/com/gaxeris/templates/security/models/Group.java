package com.gaxeris.templates.security.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(schema = "roles_model", name = "group")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Group {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "roles_model_link",
            name = "namespace_groups",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "namespace_id")
    )
    private Namespace namespace;

    public Group(String title, String description, Namespace namespace) {
        new Group(
                null,
                title,
                description,
                namespace
        );
    }

}
