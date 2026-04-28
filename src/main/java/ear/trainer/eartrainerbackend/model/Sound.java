package ear.trainer.eartrainerbackend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Sound implements Serializable {

    private static final long serialVersionUID = 1L;
    private RootNote rootNote;
    private ChordType chordType;
    private boolean correct;

    public Sound() {}

    @JsonCreator
    public Sound(
            @JsonProperty("rootNote") RootNote rootNote,
            @JsonProperty("chordType") ChordType chordType
    ) {
        this.rootNote = rootNote;
        this.chordType = chordType;
    }

    @Override
    public String toString() {
        return rootNote.toString() + " " + chordType.toString().toLowerCase();
    }
}