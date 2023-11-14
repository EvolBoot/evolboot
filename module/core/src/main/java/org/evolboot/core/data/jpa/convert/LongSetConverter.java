package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LongSetConverter implements AttributeConverter<Set<Long>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Long> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public Set<Long> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new HashSet<>() : JsonUtil.parse(dbData, Set.class, Long.class);
    }
}
