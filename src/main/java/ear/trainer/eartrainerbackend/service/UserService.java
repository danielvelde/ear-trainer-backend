package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.UserDTO;
import ear.trainer.eartrainerbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        User saved = userRepository.save(user);
        return userMapper.toDTO(saved);
    }
    // TODO
}
