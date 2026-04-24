package ear.trainer.eartrainerbackend.model;

import lombok.Getter;

@Getter
public class Sound {
    // private int octave; maybe later implement
    private final RootNote rootNote;
    private final ChordType chordType;

    public Sound(RootNote rootNote, ChordType chordType) {
        this.rootNote = rootNote;
        this.chordType = chordType;
    }

    @Override
    public String toString() {
        return rootNote.toString() + " " + chordType.toString().toLowerCase();
    }
}