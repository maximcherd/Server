package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;
}
