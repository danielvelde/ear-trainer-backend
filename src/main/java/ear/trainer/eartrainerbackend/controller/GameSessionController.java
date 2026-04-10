package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    // TODO
}
