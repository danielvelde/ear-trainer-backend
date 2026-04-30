package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.database.repository.GameSessionRepository;
import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.dto.GameSessionUpdateDto;
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

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private UserRepository userRepository;

    public GameSessionResponseDto createGameSession(GameSessionRequestDto dto) {
        List<Sound> sounds = gameContentGenerator.generateGameContent(dto.getMode(), dto.getAmountOfQuestions());

        GameSession session = new GameSession();
        session.setMode(dto.getMode());
        session.setAmountOfQuestions(dto.getAmountOfQuestions());
        session.setSounds(new ArrayList<>(sounds));
        if (dto.getUserId() != null) {
            session.setUser(userRepository.getReferenceById(dto.getUserId()));
        }
        GameSession saved = gameSessionRepository.save(session);

        GameSessionResponseDto responseDto = new GameSessionResponseDto();
        responseDto.setId(saved.getId());
        responseDto.setMode(dto.getMode());
        responseDto.setAmountOfQuestions(dto.getAmountOfQuestions());
        responseDto.setSounds(sounds);
        return responseDto;
    }

    public List<GameSession> getLatestSessions(UserDto dto) {
        return gameSessionRepository.findTop5ByUserIdOrderByCreatedAtDesc(dto.getId());
    }

    public List<GameSession> getLatestTenSessions(UserDto dto) {
        return gameSessionRepository.findTop10ByUserIdOrderByCreatedAtDesc(dto.getId());
    }

    public Map<RootNote, BooleanCounter> getScorePerNote(UserDto dto) {
        Map<RootNote, BooleanCounter> scorePerNote = new HashMap<>();
        List<GameSession> latestSessions = getLatestSessions(dto);
        for (GameSession gameSession : latestSessions) {
            List<Sound> sounds = gameSession.getSounds();
            for (Sound sound : sounds) {
                scorePerNote.computeIfAbsent(sound.getRootNote(), k -> new BooleanCounter())
                        .increment(sound.isCorrect());
            }
        }
        return scorePerNote;
    }

    public Map<String, Integer> updateGameSession(GameSessionUpdateDto dto) {
        GameSession session = gameSessionRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Session not found: " + dto.getId()));

        List<Boolean> correctAnswers = dto.getCorrectAnswers();
        ArrayList<Sound> sounds = session.getSounds();

        int correctCount = 0;
        for (int i = 0; i < sounds.size() && i < correctAnswers.size(); i++) {
            boolean isCorrect = correctAnswers.get(i);
            sounds.get(i).setCorrect(isCorrect);
            if (isCorrect) correctCount++;
        }

        int total = sounds.size();
        session.setSounds(sounds);
        session.setAccuracy(total > 0 ? (correctCount * 100 / total) : 0);
        session.setAnsweredCorrectFirstTime(correctCount);
        gameSessionRepository.save(session);

        return Map.of("correct", correctCount, "total", total, "accuracy", session.getAccuracy());
    }
}
