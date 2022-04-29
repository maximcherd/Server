package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @NotBlank
    @Column(nullable = false, updatable = false, name = "login")
    private String login;

    @NotBlank
    @Column(nullable = false, name = "passHash")
    private String passHash;

    @NotBlank
    @Column(nullable = false, name = "name")
    private String name;

    @Pattern(regexp = "^.+@.+$")
    @Column(nullable = false, name = "mail")
    private String mail;

    @Pattern(regexp = "^\\+[0-9]+$")
    @Column(nullable = false, name = "phone")
    private String phone;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private UserRole role;
}
