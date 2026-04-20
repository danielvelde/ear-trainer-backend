package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionService {
    @Autowired
    private GameContentGenerator gameContentGenerator;
    private List<Sound> sounds;

    public GameSessionResponseDto createGameSession(GameSessionRequestDto dto) {
        GameSessionResponseDto responseDto = new GameSessionResponseDto();
//        responseDto.setUserId(dto.getUserId());
        responseDto.setMode(dto.getMode());
        responseDto.setAmountOfQuestions(dto.getAmountOfQuestions());
        responseDto.setSounds(gameContentGenerator.generateGameContent(dto.getMode(), dto.getAmountOfQuestions()));
        return responseDto;
    }

}
