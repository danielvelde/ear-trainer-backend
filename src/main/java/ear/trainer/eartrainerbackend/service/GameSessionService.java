package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.database.repository.GameSessionRepository;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.model.BooleanCounter;
import ear.trainer.eartrainerbackend.model.RootNote;
import ear.trainer.eartrainerbackend.model.Sound;
import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameSessionService {
    @Autowired
    private GameContentGenerator gameContentGenerator;
    private List<Sound> sounds;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    public GameSessionResponseDto createGameSession(GameSessionRequestDto dto) {
        GameSessionResponseDto responseDto = new GameSessionResponseDto();
//        responseDto.setUserId(dto.getUserId());
        responseDto.setMode(dto.getMode());
        responseDto.setAmountOfQuestions(dto.getAmountOfQuestions());
        responseDto.setSounds(gameContentGenerator.generateGameContent(dto.getMode(), dto.getAmountOfQuestions()));
        return responseDto;
    }

    public List<GameSession> getLatestSessions(UserDto dto) {
        return gameSessionRepository.findTop5ByUserIdOrderByCreatedAtDesc(dto.getId());
    }

    public Map<RootNote, BooleanCounter> getScorePerNote(UserDto dto) {
        Map<RootNote, BooleanCounter> scorePerNote = new HashMap<>();

        List<GameSession> latestSessions = gameSessionRepository.findTop5ByUserIdOrderByCreatedAtDesc(dto.getId());

        // uit elke sessie de lists met sounds halen, per sessie de list met sounds toevoegen aan de scorepernote t
        for (int i = 0; i < latestSessions.size(); i++) {
            List<Sound> sounds = latestSessions.get(i).getSounds();
            for (Sound sound : sounds) {
                if (!scorePerNote.containsKey(sound.getRootNote())) {
                    scorePerNote.put(sound.getRootNote(), new BooleanCounter());
                }
//                scorePerNote[]
//                sound.getIsCorrect();
            }

        }



//        for (Sound sound : latestSessions.get(i).getSounds) {

//        }
        return scorePerNote;
    }


}
