package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDTO(User ent) {
        UserDto dto = new UserDto();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setEmail(ent.getEmail());
        dto.setCreatedAt(ent.getCreatedAt());
        return dto;
    }
}
