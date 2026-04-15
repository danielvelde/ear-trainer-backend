package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.client.SupabaseAuthClient;
import ear.trainer.eartrainerbackend.database.entity.User;
import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.LoginRequestDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import ear.trainer.eartrainerbackend.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupabaseAuthClient supabaseAuthClient;

    public AuthResponseDto register(RegisterRequestDto dto) {
        try {
            AuthResponseDto response = supabaseAuthClient.register(dto);

            String userId = response.getUser().getId();
            String email = response.getUser().getEmail();

            User user = new User();
            user.setId(UUID.fromString(userId));
            user.setEmail(email);

            userRepository.save(user);

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }

    public AuthResponseDto login(LoginRequestDto dto) {
        try {
            return supabaseAuthClient.login(dto);
        } catch (Exception e) {
            throw new RuntimeException("Login failed: invalid credentials");
        }
    }
}