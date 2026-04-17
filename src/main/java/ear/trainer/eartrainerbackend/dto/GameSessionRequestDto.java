package ear.trainer.eartrainerbackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class GameSessionRequestDto {
    private Long id;
    private UUID userId;
    private int mode;
    private int amountOfQuestions;

}
