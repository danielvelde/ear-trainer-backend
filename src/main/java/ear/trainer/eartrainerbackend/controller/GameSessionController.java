package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.GameSessionRequestDto;
import ear.trainer.eartrainerbackend.dto.GameSessionResponseDto;
import ear.trainer.eartrainerbackend.dto.GameSessionUpdateDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.model.BooleanCounter;
import ear.trainer.eartrainerbackend.model.RootNote;
import ear.trainer.eartrainerbackend.security.JwtFilter;
import ear.trainer.eartrainerbackend.service.GameSessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class GameSessionController {

    @Autowired
    private GameSessionService gameSessionService;

    @PostMapping("/users/{userId}/game-sessions")
    public ResponseEntity<GameSessionResponseDto> createSession(
            @PathVariable UUID userId,
            @RequestBody GameSessionRequestDto dto,
            HttpServletRequest request) {
        UUID jwtUserId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (jwtUserId == null) return ResponseEntity.status(401).build();
        if (!jwtUserId.equals(userId)) return ResponseEntity.status(403).build();
        dto.setUserId(userId);
        return ResponseEntity.status(201).body(gameSessionService.createGameSession(dto));
    }

    @GetMapping("/game/scores")
    public ResponseEntity<Map<RootNote, BooleanCounter>> getScorePerNote(HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (userId == null) return ResponseEntity.status(401).build();
        UserDto dto = new UserDto();
        dto.setId(userId);
        return ResponseEntity.ok(gameSessionService.getScorePerNote(dto));
    }

    @PutMapping("/game/session")
    public ResponseEntity<Map<String, Integer>> updateSession(@RequestBody GameSessionUpdateDto dto) {
        return ResponseEntity.ok(gameSessionService.updateGameSession(dto));
    }
}


