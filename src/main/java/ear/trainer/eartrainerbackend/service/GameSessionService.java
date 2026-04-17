package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {
    private GameContentGenerator gameContentGenerator;
    private List<Sound> sounds;

    public GameSessionResponseDto createGameSession(User user, GameSessionRequestDto Dto) {
        GameSessionResponseDto responseDto = new GameSessionResponseDto();
        responseDto.setUser(user);
        responseDto.setMode(Dto.getMode());
        responseDto.setAmountOfQuestions(Dto.getAmountOfQuestions());
        responseDto.setSounds(gameContentGenerator.generateGameContent(Dto.getMode(), Dto.getAmountOfQuestions()));
        return responseDto;
    }

}
