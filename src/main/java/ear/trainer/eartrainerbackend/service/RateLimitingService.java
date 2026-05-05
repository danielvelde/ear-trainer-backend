package ear.trainer.eartrainerbackend.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class RateLimitingService {

    // Slaat buckets op per IP-adres
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String ip) {
        return buckets.computeIfAbsent(ip, this::newBucket);
    }

    private Bucket newBucket(String ip) {
        // Configuratie: 10 verzoeken per minuut
        return Bucket.builder()
            .addLimit(
                Bandwidth.classic(
                    10,
                    Refill.intervally(10, Duration.ofMinutes(1))
                )
            )
            .build();
    }
}
