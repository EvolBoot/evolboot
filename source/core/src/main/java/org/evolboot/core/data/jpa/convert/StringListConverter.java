package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.util.JsonUtil;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringListConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new ArrayList<>() : JsonUtil.parse(dbData, List.class, String.class);
    }
}
