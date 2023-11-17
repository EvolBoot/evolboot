package org.evolboot.core.data.jpa.convert;

import jakarta.persistence.Converter;
import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Converter(autoApply = true)
public class LongListConverter implements AttributeConverter<List<Long>, String> {

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new ArrayList<>() : JsonUtil.parse(dbData, List.class, Long.class);
    }
}
