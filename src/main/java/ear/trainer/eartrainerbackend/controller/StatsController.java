package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.SessionHistoryDto;
import ear.trainer.eartrainerbackend.dto.StatsWorstDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.security.JwtFilter;
import ear.trainer.eartrainerbackend.service.StatsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/{userId}/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/worst-note")
    public ResponseEntity<StatsWorstDto> getWorstNote(@PathVariable UUID userId, HttpServletRequest request) {
        UUID jwtUserId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (jwtUserId != null && !jwtUserId.equals(userId)) return ResponseEntity.status(403).build();
        UserDto dto = new UserDto();
        dto.setId(userId);
        return ResponseEntity.ok(statsService.getWorstNote(dto));
    }

    @GetMapping("/history")
    public ResponseEntity<List<SessionHistoryDto>> getSessionHistory(@PathVariable UUID userId, HttpServletRequest request) {
        UUID jwtUserId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (jwtUserId == null || !jwtUserId.equals(userId)) return ResponseEntity.status(403).build();
        UserDto dto = new UserDto();
        dto.setId(userId);
        return ResponseEntity.ok(statsService.getSessionHistory(dto));
    }
}
