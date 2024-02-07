package ru.katiafill.airbookings.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.AttributeConverter;

public class LocalizedStringAttributeConverter implements AttributeConverter<LocalizedString, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(LocalizedStringAttributeConverter.class);

    @Override
    public String convertToDatabaseColumn(LocalizedString attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            logger.error("Cannot convert LocalizedString into JSON.");
            return null;
        }
    }

    @Override
    public LocalizedString convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, LocalizedString.class);
        } catch (JsonProcessingException e) {
            logger.error("Cannot convert JSON into LocalizedString.");
           return null;
        }
    }
}
