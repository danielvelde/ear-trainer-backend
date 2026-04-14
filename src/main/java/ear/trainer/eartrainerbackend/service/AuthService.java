package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public AuthResponseDto register(RegisterRequestDto dto) {
    // TODO
    }
}
