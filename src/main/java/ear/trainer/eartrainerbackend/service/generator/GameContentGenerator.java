package ear.trainer.eartrainerbackend.service.generator;

import java.util.ArrayList;
import java.util.List;

public class GameContentGenerator {


    public List<Sound> generateGameContent(int gameMode, int amountOfQuestions){
        List<Sound> gameContent = new ArrayList<>(amountOfQuestions);
        for (int i = 0; i < amountOfQuestions; i++){
            gameContent.add(new Sound(gameMode));
        }
        return gameContent;
    }




}
