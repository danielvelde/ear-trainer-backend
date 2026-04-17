package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import org.springframework.stereotype.Component;

@Component
public class GameSessionMapper {
    public GameSessionRequestDto toRequestDto(GameSession gameSession){
        GameSessionRequestDto dto = new GameSessionRequestDto();
        dto.setId(gameSession.getId());
        dto.setMode(gameSession.getMode());
        return dto;
    }

//    public GameSession toEntity(GameSessionRequestDto dto){
//       GameSession gameSession = new GameSession();
//        gameSession.setId(dto.getId());
//        gameSession.setMode(dto.getMode());
//        gameSession.setScore(dto.getScore());
//        gameSession.setAccuracy(dto.getAccuracy());
//        gameSession.setAverageTimeInMs(dto.getAverageTimeInMs());
//        gameSession.setCreatedAt(dto.getCreatedAt());
//        return gameSession;
//    }

    public GameSessionResponseDto toResponseDto(GameSession gameSession){
        GameSessionResponseDto dto = new GameSessionResponseDto();
        dto.setId(gameSession.getId());
        dto.setUserId(gameSession.getUser().getId());
        dto.setSounds(gameSession.getSounds());
        return dto;
    }
}
