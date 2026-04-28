package ear.trainer.eartrainerbackend.database.entity;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import ear.trainer.eartrainerbackend.model.Sound;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;

@Converter
public class SoundListConverter implements AttributeConverter<ArrayList<Sound>, String> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ArrayList<Sound> sounds) {
        if (sounds == null) return null;
        try {
            return mapper.writeValueAsString(sounds);
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to serialize sounds", e);
        }
    }

    @Override
    public ArrayList<Sound> convertToEntityAttribute(String json) {
        if (json == null) return null;
        try {
            return mapper.readValue(json, new TypeReference<>() {});
        } catch (JacksonException e) {
            throw new RuntimeException("Failed to deserialize sounds", e);
        }
    }
}
