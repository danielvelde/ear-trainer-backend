package ear.trainer.eartrainerbackend.client;

import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.LoginRequestDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SupabaseAuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String apiKey;

    @Value("${supabase.service-role-key}")
    private String serviceRoleKey;

    public AuthResponseDto register(RegisterRequestDto dto) {

        String url = supabaseUrl + "/auth/v1/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("email", dto.getEmail());
        body.put("password", dto.getPassword());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AuthResponseDto> response =
                restTemplate.postForEntity(url, request, AuthResponseDto.class);

        return response.getBody();
    }

    public void undoRegister(String userId) {
        // Let op de 'admin/users' in de URL
        String url = supabaseUrl + "/auth/v1/admin/users/" + userId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", serviceRoleKey);
        headers.set("Authorization", "Bearer " + serviceRoleKey);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
        } catch (Exception e) {
            System.err.println("Rollback failed for user: " + userId);
        }
    }

    public AuthResponseDto login(LoginRequestDto dto) {
        String url = supabaseUrl + "/auth/v1/token?grant_type=password";

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", apiKey);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("email", dto.getEmail());
        body.put("password", dto.getPassword());

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AuthResponseDto> response =
                restTemplate.postForEntity(url, request, AuthResponseDto.class);

        return response.getBody();
    }
}
