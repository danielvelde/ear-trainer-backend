package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.GameSessionDTO;
import org.springframework.stereotype.Component;

@Component
public class GameSessionMapper {
    public GameSessionDTO toDTO(GameSession gameSession){
        GameSessionDTO dto = new GameSessionDTO();
        dto.setId(gameSession.getId());
        dto.setMode(gameSession.getMode());
        dto.setAccuracy(gameSession.getAccuracy());
        dto.setAverageTimeInMs(gameSession.getAverageTimeInMs());
        dto.setCreatedAt(gameSession.getCreatedAt());
        return dto;
    }

    public GameSession toEntity(GameSessionDTO dto){
       GameSession gameSession = new GameSession();
        gameSession.setId(dto.getId());
        gameSession.setMode(dto.getMode());
        gameSession.setScore(dto.getScore());
        gameSession.setAccuracy(dto.getAccuracy());
        gameSession.setAverageTimeInMs(dto.getAverageTimeInMs());
        gameSession.setCreatedAt(dto.getCreatedAt());
        return gameSession;
    }
}
