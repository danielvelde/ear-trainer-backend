package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.dto.StatsWorstDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.model.BooleanCounter;
import ear.trainer.eartrainerbackend.model.RootNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
