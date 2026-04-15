package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.client.SupabaseAuthClient;
import ear.trainer.eartrainerbackend.database.repository.UserRepository;
import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import ear.trainer.eartrainerbackend.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private SupabaseAuthClient supabaseAuthClient;

    public AuthResponseDto register(RegisterRequestDto dto) {
        try{
            return supabaseAuthClient.register(dto);
        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }
}