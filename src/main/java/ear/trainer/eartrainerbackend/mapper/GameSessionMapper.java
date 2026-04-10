package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.GameSessionDTO;

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

    public GameSession toEntity(GameSessionDTO gameSessionDTO){
        // TODO
        return new GameSession();
    }
}
