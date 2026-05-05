package ear.trainer.eartrainerbackend.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class LoggingClient {

    @Value("${logger.url}")
    private String loggerUrl;

    private final RestClient restClient = RestClient.create();

    public void log(String apiEndpoint){
        restClient.post()
                .uri(loggerUrl, apiEndpoint)
                .retrieve()
                .toBodilessEntity();
    }

}
