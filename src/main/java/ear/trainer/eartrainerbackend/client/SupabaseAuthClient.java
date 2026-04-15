package ear.trainer.eartrainerbackend.client;

import ear.trainer.eartrainerbackend.dto.AuthResponseDto;
import ear.trainer.eartrainerbackend.dto.RegisterRequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SupabaseAuthClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String SUPABASE_URL = "https://YOUR_PROJECT.supabase.co";
    private final String API_KEY = "YOUR_ANON_KEY";

    public AuthResponseDto register(RegisterRequestDto dto) {
        String url = SUPABASE_URL + "/auth/v1/signup";

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
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
