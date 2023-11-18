package org.evolboot.common.domain.dictkey.repository.jpa.convert;

import com.google.common.collect.Lists;
import jakarta.persistence.AttributeConverter;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.common.domain.dictkey.entity.DictKeyLocale;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author evol
 * @date 2023-05-07 12:29:33
 */
public class DictKeyLocaleListConverter implements AttributeConverter<List<DictKeyLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<DictKeyLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<DictKeyLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, DictKeyLocale.class);
    }
}
