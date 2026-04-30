package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.SongRecommendationDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.security.JwtFilter;
import ear.trainer.eartrainerbackend.service.SongRecommendationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/recommendations")
public class SongRecommendationController {

    @Autowired
    private SongRecommendationService songRecommendationService;

    @GetMapping("/worst")
    public ResponseEntity<List<SongRecommendationDto>> getRecommendationsForWorstNote(HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        UserDto userDto = new UserDto();
        userDto.setId(userId);
        return ResponseEntity.ok(songRecommendationService.getRecommendationsForWorstNote(userDto));
    }
}
