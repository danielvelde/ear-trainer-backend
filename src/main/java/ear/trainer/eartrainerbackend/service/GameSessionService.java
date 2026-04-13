package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.GameSessionDTO;
import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {
    private GameContentGenerator gameContentGenerator;
    private List<Sound> sounds;

    public GameSession createGameSession(User user, GameSessionDTO gameSessionDTO) {
        GameSession gameSession = new GameSession();
        gameSession.setUser(user);
        gameSession.setMode(gameSessionDTO.getMode());
        gameSession.setAmountOfQuestions(gameSessionDTO.getAmountOfQuestions());

    }

}
