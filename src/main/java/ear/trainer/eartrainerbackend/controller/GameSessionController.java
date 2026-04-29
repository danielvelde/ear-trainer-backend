package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.dto.GameSessionUpdateDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.security.JwtFilter;
import ear.trainer.eartrainerbackend.service.GameSessionService;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<GameSessionResponseDto> getSession(GameSessionRequestDto dto, HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        dto.setUserId(userId);
        return ResponseEntity.ok(gameSessionService.createGameSession(dto));
    }

    // Deze is misschien overbodig?
    @GetMapping("/getscores")
    public ResponseEntity<?> getScorePerNote(HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        UserDto dto = new UserDto();
        dto.setId(userId);
        return ResponseEntity.ok(gameSessionService.getScorePerNote(dto));
    }
    @PutMapping("/session")
    public ResponseEntity<?> updateSession(@RequestBody GameSessionUpdateDto dto) {
        return ResponseEntity.ok(gameSessionService.updateGameSession(dto));
    }
}


