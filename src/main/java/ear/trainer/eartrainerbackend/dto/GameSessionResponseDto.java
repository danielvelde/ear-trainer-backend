package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.service.generator.Sound;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class GameSessionResponseDto {
        private Long id;
        private UUID userId;
        private List<Sound> sounds;
}
