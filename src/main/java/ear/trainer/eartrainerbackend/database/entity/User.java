package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Email
    private String email;

    private int defaultDifficulty; // 0 = single notes, 1 = simple chords

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;
}
