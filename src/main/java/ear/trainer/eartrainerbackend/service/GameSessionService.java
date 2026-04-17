package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {
    private GameContentGenerator gameContentGenerator;
    private List<Sound> sounds;

    public GameSession createGameSession(String user, GameSessionRequestDto Dto) {
        GameSession gameSession = new GameSession();
        gameSession.setUser(user);
        gameSession.setMode(Dto.getMode());
        gameSession.setAmountOfQuestions(Dto.getAmountOfQuestions());
        return gameSession;
    }

}
