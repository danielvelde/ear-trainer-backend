package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.dto.FreesoundPackApiDto;
import ear.trainer.eartrainerbackend.dto.FreesoundResponseDto;
import ear.trainer.eartrainerbackend.dto.FreesoundSoundApiDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class FreesoundService {

    private static final String FREESOUND_API_BASE = "https://freesound.org/apiv2";

    @Value("${freesound.api-key}")
    private String apiKey;

    @Value("${freesound.pack-id}")
    private String packId;

    @Value("${freesound.cache-dir}")
    private String cacheDir;

    @Autowired
    private RestTemplate restTemplate;

    private final Map<String, FreesoundSoundApiDto> soundMetadata = new ConcurrentHashMap<>();

    @PostConstruct
    public void initCache() {
        try {
            Files.createDirectories(Path.of(cacheDir));
            fetchAndCachePackSounds();
        } catch (Exception e) {
            log.error("Failed to initialize Freesound cache", e);
        }
    }

    private void fetchAndCachePackSounds() {
        String url = FREESOUND_API_BASE + "/packs/" + packId + "/sounds/"
                + "?token=" + apiKey + "&fields=id,name,previews&page_size=150";

        while (url != null) {
            FreesoundPackApiDto response = restTemplate.getForObject(url, FreesoundPackApiDto.class);
            if (response == null || response.getResults() == null) break;

            for (FreesoundSoundApiDto sound : response.getResults()) {
                String id = String.valueOf(sound.getId());
                soundMetadata.put(id, sound);
                downloadIfNotCached(sound);
            }

            String next = response.getNext();
            if (next != null && !next.contains("token=")) {
                next = next + "&token=" + apiKey;
            }
            url = next;
        }

        log.info("Freesound cache initialized: {} sounds available", soundMetadata.size());
    }

    private void downloadIfNotCached(FreesoundSoundApiDto sound) {
        Path filePath = Path.of(cacheDir, sound.getId() + ".mp3");
        if (Files.exists(filePath)) return;

        Map<String, String> previews = sound.getPreviews();
        if (previews == null) return;

        String previewUrl = previews.get("preview-hq-mp3");
        if (previewUrl == null) previewUrl = previews.get("preview-lq-mp3");
        if (previewUrl == null) return;

        try {
            byte[] bytes = restTemplate.getForObject(previewUrl, byte[].class);
            if (bytes != null) {
                Files.write(filePath, bytes);
                log.debug("Cached sound {}: {}", sound.getId(), sound.getName());
            }
        } catch (Exception e) {
            log.warn("Failed to download sound {}: {}", sound.getId(), e.getMessage());
        }
    }

    public byte[] getAudioBytes(String soundId) throws IOException {
        Path filePath = Path.of(cacheDir, soundId + ".mp3");
        if (!Files.exists(filePath)) {
            throw new IOException("Sound not cached: " + soundId);
        }
        return Files.readAllBytes(filePath);
    }

    public List<FreesoundResponseDto> getAvailableSounds() {
        return soundMetadata.entrySet().stream()
                .map(e -> new FreesoundResponseDto(e.getKey(), e.getValue().getName()))
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .toList();
    }
}