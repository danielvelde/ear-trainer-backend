package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "game_sessions")
public class GameSessionEntity {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_entity_id")
    private UserEntity userEntity;
    private int mode; // 0 = single note, 1 = simple chords -- dit fungeert eigenlijk ook als difficulty
    private int score; // 100 = max
    private int accuracy;
    private int averageTimeInMs;
    private int answeredCorrectFirstTime;
    private int answeredCorrectSecondTime;
    private Timestamp createdAt;

    // TODO
}
