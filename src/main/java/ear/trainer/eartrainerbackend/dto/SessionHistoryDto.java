package ear.trainer.eartrainerbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class SessionHistoryDto {
    private int accuracy;
    private int score;
    private Timestamp createdAt;

    public SessionHistoryDto(int accuracy, int score, Timestamp createdAt) {
        this.accuracy = accuracy;
        this.score = score;
        this.createdAt = createdAt;
    }
}
