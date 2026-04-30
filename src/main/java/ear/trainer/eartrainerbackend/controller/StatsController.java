package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.StatsWorstDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import ear.trainer.eartrainerbackend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/stats")
public class StatsController {

    @Autowired
    private StatsService statsService;

    @GetMapping("/worstnote")
    public ResponseEntity<StatsWorstDto> getWorstNote(@RequestParam UUID userId){
        UserDto dto = new UserDto();
        dto.setId(userId);
        return ResponseEntity.ok(statsService.getWorstNote(dto));
    }
}
