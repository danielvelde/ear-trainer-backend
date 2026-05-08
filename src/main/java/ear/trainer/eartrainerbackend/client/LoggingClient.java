package ear.trainer.eartrainerbackend.client;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

@Service
public class LoggingClient {

    @Value("${logger.url}")
    private String loggerUrl;

    private WebSocketSession session;

    @PostConstruct
    public void connect() throws Exception {
        WebSocketClient client = new StandardWebSocketClient();
        session = client.execute(new AbstractWebSocketHandler() {
            @Override
            public void handleTextMessage(WebSocketSession s, TextMessage message) {
                // handle messages from Node server
            }

            @Override
            public void afterConnectionClosed(WebSocketSession s, CloseStatus status) {
                // optionally reconnect here
            }
        }, "ws://localhost:8081/logs").get();
    }




    private final RestClient restClient = RestClient.create();

   // this is for normal REST (HTTP)
    public void log(String activity) throws IOException {
//        restClient.post()
//                .uri(loggerUrl, "/logs")
//                .retrieve()
//                .toBodilessEntity();

        WebSocketMessage<String> message = new TextMessage("test");
        session.sendMessage(message);
    }

}
