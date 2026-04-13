package ear.trainer.eartrainerbackend.service.generator;


import java.util.concurrent.ThreadLocalRandom;

public class Sound {
    // private int octave; maybe later implement
    private RootNote rootNote;
    private ChordType chordType;





    public Sound(int gameMode){

        RootNote[] notes = RootNote.values();
        rootNote = notes[ThreadLocalRandom.current().nextInt(notes.length)];

        // if game mode = 0, then no chord
        // if game mode = 1, then generate random bool that determines major or minor chord
        if (gameMode == 0){
            chordType = ChordType.SINGLENOTE;
        } else if (gameMode == 1){
            chordType = ThreadLocalRandom.current().nextBoolean() ? ChordType.MINOR : ChordType.MAJOR;

        }
    }

    public String toString(){
        return rootNote.toString() + " " + chordType.toString().toLowerCase();
    }
}
