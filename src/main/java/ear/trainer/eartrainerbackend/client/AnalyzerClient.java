package ear.trainer.eartrainerbackend.client;

import ear.trainer.eartrainerbackend.dto.AnalyzerResponseDto;
import ear.trainer.eartrainerbackend.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyzerClient {

    @Autowired
    private LoggingClient loggingClient;

    @Value("${analyzer.url}")
    private String analyzerUrl;

    @Value("${analyzer.internal-token}")
    private String internalToken;

    private final RestTemplate restTemplate;

    public AnalyzerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AnalyzerResponseDto analyze(UserDto userDto) {
        return getLlmResults(userDto);
    }

    public AnalyzerResponseDto getLlmResults(UserDto userDto){
        AnalyzerResponseDto resDto = new AnalyzerResponseDto();
        String url = analyzerUrl + "/analyze";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> body = new HashMap<>();
        body.put("userId", userDto.getId().toString());

        loggingClient.log("/analyze");

        HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<AnalyzerResponseDto> response =
                restTemplate.postForEntity(url, request, AnalyzerResponseDto.class);

        AnalyzerResponseDto responseBody = response.getBody();
        resDto.setText(responseBody != null ? responseBody.getText() : "");
        return resDto;
    }
}
