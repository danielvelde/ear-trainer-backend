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
    }

}
