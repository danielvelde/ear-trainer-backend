package ear.trainer.eartrainerbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreesoundSoundApiDto {
    private long id;
    private String name;
    private Map<String, String> previews;
}