package ear.trainer.eartrainerbackend.controller;

import ear.trainer.eartrainerbackend.dto.FreesoundResponseDto;
import ear.trainer.eartrainerbackend.service.FreesoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/audio")
public class FreesoundController {

    @Autowired
    private FreesoundService freesoundService;

    @GetMapping("/sounds")
    public ResponseEntity<List<FreesoundResponseDto>> getSoundsList() {
        return ResponseEntity.ok(freesoundService.getAvailableSounds());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<byte[]> getSound(@PathVariable String id) throws IOException {
        byte[] audioBytes = freesoundService.getAudioBytes(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + id + ".mp3\"")
                .body(audioBytes);
    }
}