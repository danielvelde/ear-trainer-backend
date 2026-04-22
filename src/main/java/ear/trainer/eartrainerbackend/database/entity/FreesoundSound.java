package ear.trainer.eartrainerbackend.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "freesound_sound")
    @Getter
    @Setter
    @NoArgsConstructor
    public class FreesoundSound {

        @Id
        private String id;                 // freesound id

        @Column(nullable = false)
        private String name;

        @Column(name = "object_key", nullable = false)
        private String objectKey;          // e.g. "sounds/12345.mp3"

        @Column(name = "content_type", nullable = false)
        private String contentType = "audio/mpeg";
}
