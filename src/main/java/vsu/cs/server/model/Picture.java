package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "picture")
public class Picture {
    @Id
    @NotBlank
    @GeneratedValue
    @Column(nullable = false, updatable = false, insertable = false)
    private UUID id;

    @Null
    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "login")
    private User creator;

    @NotBlank
    @Column(nullable = false, name = "name")
    private String name;

    @Null
    @ManyToOne
    @JoinColumn(name = "source", referencedColumnName = "id")
    private Picture source;

    @NotBlank
    @Column(nullable = false, name = "url")
    private String url;
}
