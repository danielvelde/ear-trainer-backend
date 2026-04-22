package ear.trainer.eartrainerbackend.service;

import ear.trainer.eartrainerbackend.database.entity.FreesoundSound;
import ear.trainer.eartrainerbackend.database.repository.FreesoundSoundRepository;
import ear.trainer.eartrainerbackend.dto.FreesoundPackApiDto;
import ear.trainer.eartrainerbackend.dto.FreesoundResponseDto;
import ear.trainer.eartrainerbackend.dto.FreesoundSoundApiDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@Service
public class FreesoundService {

    private static final String FREESOUND_API_BASE = "https://freesound.org/apiv2";

    @Value("${freesound.api-key}") private String apiKey;
    @Value("${freesound.pack-id}") private String packId;
    @Value("${supabase.storage.bucket}") private String bucket;

    @Autowired private RestTemplate restTemplate;
    @Autowired private S3Client s3;
    @Autowired private FreesoundSoundRepository repository;
//    private final Map<String, FreesoundSoundApiDto> soundMetadata = new ConcurrentHashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        try {
//            Files.createDirectories(Path.of(cacheDir));
            fetchAndUploadPackSounds();
        } catch (Exception e) {
            log.error("Failed to sync Freesound pack", e);
        }
    }

    private void fetchAndUploadPackSounds() {
        String url = FREESOUND_API_BASE + "/packs/" + packId + "/sounds/"
                + "?token=" + apiKey + "&fields=id,name,previews&page_size=150";

        while (url != null) {
            FreesoundPackApiDto response = restTemplate.getForObject(url, FreesoundPackApiDto.class);
            if (response == null || response.getResults() == null) break;

            for (FreesoundSoundApiDto sound : response.getResults()) {
//                String id = String.valueOf(sound.getId());
//                soundMetadata.put(id, sound);
                uploadIfMissing(sound);
            }

            String next = response.getNext();
            if (next != null && !next.contains("token=")) {
                next = next + "&token=" + apiKey;
            }
            url = next;
        }

        log.info("Freesound cache initialized: {} sounds available", repository.count());
    }

    private void uploadIfMissing(FreesoundSoundApiDto sound) {
        String id = String.valueOf(sound.getId());
        if (repository.existsById(id)) return;

        Map<String, String> previews = sound.getPreviews();
        if (previews == null) return;
        String previewUrl = previews.getOrDefault("preview-hq-mp3", previews.get("preview-lq-mp3"));
        if (previewUrl == null) return;


        try {
            byte[] bytes = restTemplate.getForObject(previewUrl, byte[].class);
            if (bytes == null) return;


            String objectKey = "sounds/" + id + ".mp3";

            s3.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(objectKey)
                            .contentType("audio/mpeg")
                            .build(),
                    RequestBody.fromBytes(bytes)
            );

            FreesoundSound entity = new FreesoundSound();
            entity.setId(id);
            entity.setName(sound.getName());
            entity.setObjectKey(objectKey);
            entity.setContentType("audio/mpeg");
            repository.save(entity);


            log.debug("Uploaded {} ({} bytes) -> {}", id, bytes.length, objectKey);
        } catch (Exception e) {
            log.warn("Failed to download sound {}: {}", sound.getId(), e.getMessage());
        }
    }

    public byte[] getAudioBytes(String id) {
        FreesoundSound sound = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sound not found: " + id));

        try (ResponseInputStream<GetObjectResponse> obj = s3.getObject(
                GetObjectRequest.builder()
                        .bucket(bucket)
                        .key(sound.getObjectKey())
                        .build())) {
            return obj.readAllBytes();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read " + sound.getObjectKey(), e);
        }
    }

    public List<FreesoundResponseDto> getAvailableSounds() {
        return repository.findAllByOrderByNameAsc().stream()
                .map(v -> new FreesoundResponseDto(v.getId(), v.getName()))
                .toList();
    }
}