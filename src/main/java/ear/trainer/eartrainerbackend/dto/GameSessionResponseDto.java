package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class GameSessionResponseDto {
        private User user;
        private UUID userId;
        private int mode;
        private int amountOfQuestions;
        private List<Sound> sounds;
}
