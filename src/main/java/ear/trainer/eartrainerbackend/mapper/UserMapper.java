package ear.trainer.eartrainerbackend.mapper;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toEntity(UserDto dto){ // Delete -> too genericc
        User ent = new User();
        ent.setName(dto.getName());
        ent.setEmail(dto.getEmail());
        return ent;
    }

    public User fromRegisterRequestDto(RegisterRequestDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        return user;
    }


    public UserDto toDTO(User ent) { // Delete -> too generic
        UserDto dto = new UserDto();
        dto.setId(ent.getId());
        dto.setName(ent.getName());
        dto.setEmail(ent.getEmail());
        dto.setCreatedAt(ent.getCreatedAt());
        return dto;
    }
}
