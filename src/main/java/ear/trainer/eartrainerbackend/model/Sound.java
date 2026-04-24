package ear.trainer.eartrainerbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Sound {
    // private int octave; maybe later implement
    private final RootNote rootNote;
    private final ChordType chordType;

    @Setter
    private boolean isCorrect;

    public Sound(RootNote rootNote, ChordType chordType) {
        this.rootNote = rootNote;
        this.chordType = chordType;
    }

    @Override
    public String toString() {
        return rootNote.toString() + " " + chordType.toString().toLowerCase();
    }
}