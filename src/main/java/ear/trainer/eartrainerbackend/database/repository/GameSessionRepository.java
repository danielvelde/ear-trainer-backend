package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.GameSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GameSessionRepository extends JpaRepository<GameSessionEntity, Long> {
}
