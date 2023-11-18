package org.evolboot.common.domain.dictvalue.repository.jpa.convert;

import com.google.common.collect.Lists;
import jakarta.persistence.AttributeConverter;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.common.domain.dictvalue.entity.DictValueLocale;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author evol
 * @date 2023-05-07 12:55:06
 */
public class DictValueLocaleListConverter implements AttributeConverter<List<DictValueLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<DictValueLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<DictValueLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, DictValueLocale.class);
    }
}
