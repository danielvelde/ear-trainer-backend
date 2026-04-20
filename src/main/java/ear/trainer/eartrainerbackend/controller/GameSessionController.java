package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.service.GameSessionService;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/game")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;



    @GetMapping("/session")
    public ResponseEntity<GameSessionResponseDto> getSession(GameSessionRequestDto dto) {
        return ResponseEntity.ok(gameSessionService.createGameSession(dto));
    }

//    @PutMapping // I think this could be used for actually storing the score after the game is finished

//    @DeleteMapping // Maybe for when person delet account

    // TODO
}
