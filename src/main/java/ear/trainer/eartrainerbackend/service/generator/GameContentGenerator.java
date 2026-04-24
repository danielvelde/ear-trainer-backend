package ear.trainer.eartrainerbackend.service.generator;

import ear.trainer.eartrainerbackend.model.ChordType;
import ear.trainer.eartrainerbackend.model.RootNote;
import ear.trainer.eartrainerbackend.model.Sound;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GameContentGenerator {

    public List<Sound> generateGameContent(int gameMode, int amountOfQuestions) {
        List<Sound> gameContent = new ArrayList<>(amountOfQuestions);
        for (int i = 0; i < amountOfQuestions; i++) {
            gameContent.add(randomSound(gameMode));
        }
        return gameContent;
    }

    // if game mode = 0, then no chord
    // if game mode = 1, then generate random bool that determines major or minor chord
    private Sound randomSound(int gameMode) {
        RootNote[] notes = RootNote.values();
        RootNote rootNote = notes[ThreadLocalRandom.current().nextInt(notes.length)];

        ChordType chordType = null;
        if (gameMode == 0) {
            chordType = ChordType.SINGLENOTE;
        } else if (gameMode == 1) {
            chordType = ThreadLocalRandom.current().nextBoolean() ? ChordType.MINOR : ChordType.MAJOR;
        }

        return new Sound(rootNote, chordType);
    }
}