package ear.trainer.eartrainerbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreesoundPackApiDto {
    private int count;
    private String next;
    private List<FreesoundSoundApiDto> results;
}