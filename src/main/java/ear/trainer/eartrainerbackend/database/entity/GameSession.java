package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "game_sessions")
public class GameSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;

    private int mode; // 0 = single note, 1 = simple chords 2 = add sus chords and 7ths -- this functions as the difficulty

    private int score; // 100 = max

    private int accuracy; // 0 to 100 percent

    private int averageTimeInMs;

    private int answeredCorrectFirstTime;

    private int answeredCorrectSecondTime;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    // TODO
}
