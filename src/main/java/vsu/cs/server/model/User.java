package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    private Long id;

    @Column(nullable = false, updatable = false, name = "login")
    private String login;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "name")
    private String name;

    @Pattern(regexp = "^.+@.+$")
    @Column(nullable = false, name = "mail")
    private String mail;

    @Pattern(regexp = "^\\+[0-9]+$")
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
}
