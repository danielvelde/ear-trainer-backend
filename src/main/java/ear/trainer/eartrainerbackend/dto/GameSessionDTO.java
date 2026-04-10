package ear.trainer.eartrainerbackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class GameSessionDTO {
    private Long id;
    private int mode;
    private int accuracy;
    private int averageTimeInMs;
    private Timestamp createdAt;
}
