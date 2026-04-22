package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.FreesoundSound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FreesoundSoundRepository extends JpaRepository<FreesoundSound, String> {
    interface SoundListView {
        String getId();
        String getName();
    }
    List<SoundListView> findAllByOrderByNameAsc();
}