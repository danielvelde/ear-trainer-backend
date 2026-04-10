package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSettingsRepository extends JpaRepository<Settings, Long> {

}
