package vsu.cs.server.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_role")
public class UserRole {
    @Id
    @NotBlank
    @GeneratedValue
    @Column(updatable = false, insertable = false)
    private UUID id;

    @NotBlank
    @Column(nullable = false, name = "name")
    private String name;
}
