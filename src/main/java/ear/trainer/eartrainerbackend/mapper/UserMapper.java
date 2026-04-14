package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto dto){
        User ent = new User();
        ent.setName(dto.getName());
        ent.setEmail(dto.getEmail());
        return ent;
    }

    public UserDto toDTO(User ent) {
        UserDto dto = new UserDto();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setEmail(ent.getEmail());
        dto.setCreatedAt(ent.getCreatedAt());
        return dto;
    }
}
