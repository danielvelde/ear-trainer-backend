package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.model.Sound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameSessionResponseDto {
        private Long id;
        private int mode;
        private int amountOfQuestions;
        private List<Sound> sounds;
}
