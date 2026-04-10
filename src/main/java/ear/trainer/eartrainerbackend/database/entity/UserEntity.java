package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;
    private String name;
    private String email;
    private Timestamp createdAt;
    // TODO
}
