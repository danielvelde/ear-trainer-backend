package ear.trainer.eartrainerbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongRecommendationDto {
    private String track;
    private String artist;
    private String trackId;
    private String rootnote;
}
