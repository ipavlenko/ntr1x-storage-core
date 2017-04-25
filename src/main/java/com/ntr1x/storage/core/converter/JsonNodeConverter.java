package com.ntr1x.storage.core.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.ntr1x.storage.core.services.ISerializationService;

@Converter(autoApply = true)
public class JsonNodeConverter implements AttributeConverter<JsonNode, String> {

    private static ISerializationService serialization;
    
    public static void setSerializationService(ISerializationService serialization) {
        JsonNodeConverter.serialization = serialization;
    }
    
    @Override
    public String convertToDatabaseColumn(JsonNode node) {
        if (node == null) return null;
        return serialization.toJSONStringJackson(node);
    }

    @Override
    public JsonNode convertToEntityAttribute(String string) {
        if (string == null) return null;
        return serialization.readJSONNodeJackson(string);
    }
}