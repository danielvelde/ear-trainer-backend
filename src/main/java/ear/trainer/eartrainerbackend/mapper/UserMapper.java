package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto){
        User ent = new User();
        ent.setName(dto.getName());
        ent.setEmail(dto.getEmail());
        return ent;
    }

    public UserDTO toDTO(User ent) {
        UserDTO dto = new UserDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setEmail(ent.getEmail());
        dto.setCreatedAt(ent.getCreatedAt());
        return dto;
    }
}
