package ear.trainer.eartrainerbackend.service.generator;

import java.util.ArrayList;
import java.util.Random;

public class GameContentGenerator {
    private Random random; // used for generating the (root)notes and chord type


    public ArrayList<Sound> generateGameContent(int gameMode){
        ChordType chordType;
        switch (gameMode) {
            case 0:

        }

        ArrayList<Sound> gameContent = new ArrayList<>();
        for (int i = 0; i < 12; i++){
            // TODO
        }

        return gameContent;
    }

    private Sound generateNote() {
        Sound sound = new Sound();
        return sound;
    }
}
