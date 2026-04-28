package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.model.Sound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionResponseDto {
        private Long id;
//        private UUID userId;
        private int mode;
        private int amountOfQuestions;
        private List<Sound> sounds;
}
