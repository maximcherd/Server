package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, insertable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "login")
    private User creator;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "source", referencedColumnName = "id")
    private Picture source;

    @Column(nullable = false, name = "url")
    private String url;
}
