package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.UserDTO;
import ear.trainer.eartrainerbackend.exception.UserAlreadyExistsException;
import ear.trainer.eartrainerbackend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserDTO createUser(UserDTO dto) {
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException(dto.getEmail());
        }
        User user = userMapper.toEntity(dto);
        User userCreated = userRepository.save(user);
        return userMapper.toDTO(userCreated);
    }

    public UserDTO getUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

}
