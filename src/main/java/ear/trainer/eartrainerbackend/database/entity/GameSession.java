package ear.trainer.eartrainerbackend.database.entity;

import ear.trainer.eartrainerbackend.service.generator.Sound;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "game_sessions")
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int mode; // 0 = single note, 1 = simple chords 2 = add sus chords and 7ths -- this functions as the difficulty

    private int amountOfQuestions;

    private ArrayList<Sound> sounds;

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
