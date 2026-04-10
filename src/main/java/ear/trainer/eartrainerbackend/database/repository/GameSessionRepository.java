package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}
