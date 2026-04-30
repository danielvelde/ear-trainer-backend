package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.SongRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRecommendationRepository extends JpaRepository<SongRecommendation, Long> {
    List<SongRecommendation> findByRootnote(String rootnote);
}
