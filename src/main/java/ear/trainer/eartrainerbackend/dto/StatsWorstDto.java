package ear.trainer.eartrainerbackend.dto;

import ear.trainer.eartrainerbackend.model.RootNote;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsWorstDto {
    private RootNote worstNote;

    public StatsWorstDto(RootNote rootNote) {
        this.worstNote = rootNote;
    }
}
