package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.UserDTO;

public class UserMapper {
    public User toEntity(UserDTO dto){
        User ent = new User();
        ent.setId(dto.getId());
        ent.setName(dto.getName());
        ent.setEmail(dto.getEmail());
        ent.setCreatedAt(dto.getCreatedAt());
        return ent;
    }

    public UserDTO toDTO(User ent) {
        UserDTO dto = new UserDTO();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        return dto;
    }
}
