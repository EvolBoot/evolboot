package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.util.JsonUtil;

import javax.persistence.AttributeConverter;
import java.util.Map;
import java.util.Objects;

public class StringStringMapConverter implements AttributeConverter<Map<String, String>, String> {
    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        return Objects.isNull(dbData) ? null : JsonUtil.parse(dbData, Map.class, String.class, String.class);
    }
}
