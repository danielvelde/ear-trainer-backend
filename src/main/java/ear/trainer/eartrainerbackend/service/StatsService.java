package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.SessionHistoryDto;
import ear.trainer.eartrainerbackend.dto.StatsWorstDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.model.BooleanCounter;
import ear.trainer.eartrainerbackend.model.RootNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class StatsService {

    @Autowired
    private GameSessionService gameSessionService;

    public StatsWorstDto getWorstNote(UserDto dto) {
        Map<RootNote, BooleanCounter> scorePerNote = gameSessionService.getScorePerNote(dto);
        RootNote worstNote = null;
        float percentageWorst = 1;
        for (Map.Entry<RootNote, BooleanCounter> entry : scorePerNote.entrySet()){
            BooleanCounter booleanCounter = entry.getValue();
            float percentageEntry = (float) (booleanCounter.getFalseCount() / (booleanCounter.getTrueCount() + booleanCounter.getFalseCount()));
            if (percentageEntry < percentageWorst) {
                worstNote = entry.getKey();
                percentageWorst = percentageEntry;
            }
        }
        return new StatsWorstDto(worstNote);
    }

    public List<SessionHistoryDto> getSessionHistory(UserDto dto) {
        List<GameSession> sessions = gameSessionService.getLatestTenSessions(dto);
        List<SessionHistoryDto> history = sessions.stream()
                .map(s -> new SessionHistoryDto(s.getAccuracy(), s.getScore(), s.getCreatedAt()))
                .collect(java.util.stream.Collectors.toList());
        Collections.reverse(history);
        return history;
    }
}
