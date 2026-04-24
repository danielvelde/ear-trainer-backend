package ear.trainer.eartrainerbackend.database.repository;

import ear.trainer.eartrainerbackend.database.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    List<GameSession> findTop5ByUserIdOrderByCreatedAtDesc(UUID userId);
}
