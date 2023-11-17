package org.evolboot.core.data.jpa.convert;

import jakarta.persistence.Converter;
import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Converter(autoApply = true)
public class StringSetConverter implements AttributeConverter<Set<String>, String> {
    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new HashSet<>() : JsonUtil.parse(dbData, Set.class, String.class);
    }
}
