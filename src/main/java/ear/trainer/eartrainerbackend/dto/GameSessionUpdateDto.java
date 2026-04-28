package ear.trainer.eartrainerbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameSessionUpdateDto {
    private Long id;
    private List<Boolean> correctAnswers;
}
