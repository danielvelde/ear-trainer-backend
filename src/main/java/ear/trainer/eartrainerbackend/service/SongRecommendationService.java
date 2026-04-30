package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.SongRecommendation;
import ear.trainer.eartrainerbackend.database.repository.SongRecommendationRepository;
import ear.trainer.eartrainerbackend.dto.SongRecommendationDto;
import ear.trainer.eartrainerbackend.dto.StatsWorstDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongRecommendationService {

    @Autowired
    private SongRecommendationRepository repository;

    @Autowired
    private StatsService statsService;

    public List<SongRecommendationDto> getRecommendationsForWorstNote(UserDto userDto) {
        StatsWorstDto worst = statsService.getWorstNote(userDto);
        if (worst.getWorstNote() == null) {
            return List.of();
        }
        String rootnote = worst.getWorstNote().name();
        List<SongRecommendation> recommendations = repository.findByRootnote(rootnote);
        return recommendations.stream().map(this::toDto).toList();
    }

    private SongRecommendationDto toDto(SongRecommendation rec) {
        SongRecommendationDto dto = new SongRecommendationDto();
        dto.setTrack(rec.getTrack());
        dto.setArtist(rec.getArtist());
        dto.setTrackId(rec.getTrackId());
        dto.setRootnote(rec.getRootnote());
        return dto;
    }
}
