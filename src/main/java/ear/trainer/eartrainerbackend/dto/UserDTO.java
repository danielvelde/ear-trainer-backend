package ear.trainer.eartrainerbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;
@Setter
@Getter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private int defaultDifficulty;
    private Timestamp createdAt;

}
