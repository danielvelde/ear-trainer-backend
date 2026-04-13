package ear.trainer.eartrainerbackend;

import ear.trainer.eartrainerbackend.service.generator.GameContentGenerator;
import ear.trainer.eartrainerbackend.service.generator.Sound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;

@SpringBootApplication
public class EartrainerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EartrainerBackendApplication.class, args);

        GameContentGenerator gameContentGenerator = new GameContentGenerator();
        List<Sound> sounds = gameContentGenerator.generateGameContent(0, 12);
        for (Sound sound : sounds) {
            System.out.println(sound.toString());
        }
    }

}
