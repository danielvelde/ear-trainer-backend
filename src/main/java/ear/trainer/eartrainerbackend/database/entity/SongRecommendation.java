package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "song_recommendations")
public class SongRecommendation {

    @Id
    private Long id;

    private String rootnote;

    private String track;

    private String artist;

    @Column(name = "\"track-id\"")
    private String trackId;
}
