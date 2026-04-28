package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.model.BooleanCounter;
import ear.trainer.eartrainerbackend.model.RootNote;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class AnalyzerRequestDto {
    private Map<RootNote, BooleanCounter> scorePerNote;
}
