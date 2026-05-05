package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.AnalyzerResponseDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.security.JwtFilter;
import ear.trainer.eartrainerbackend.service.AnalyzerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/analyzer")
public class AnalyzerController {
    @Autowired
    private AnalyzerService analyzerService;



    @GetMapping("/analytics")
    public ResponseEntity<AnalyzerResponseDto> analyze(HttpServletRequest request) {
        UUID userId = (UUID) request.getAttribute(JwtFilter.USER_ID_ATTR);
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        UserDto dto = new UserDto();
        dto.setId(userId);
        Object email = request.getAttribute(JwtFilter.USER_EMAIL_ATTR);
        if (email != null) {
            dto.setEmail(email.toString());
        }
        return ResponseEntity.ok(analyzerService.analyze(dto));
    }
}
