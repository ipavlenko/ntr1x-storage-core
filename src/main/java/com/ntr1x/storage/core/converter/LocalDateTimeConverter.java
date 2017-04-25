package com.ntr1x.storage.core.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime date) {
        return date == null ? null : Timestamp.valueOf(date);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp date) {
        return date == null ? null : date.toLocalDateTime();
    }
}