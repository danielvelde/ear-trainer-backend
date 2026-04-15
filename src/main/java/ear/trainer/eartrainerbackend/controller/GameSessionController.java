package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

//    @GetMapping

//    @PostMapping

//    @PutMapping // I think this could be used for actually storing the score after the game is finished

//    @DeleteMapping // Maybe for when person delet account

    // TODO
}
