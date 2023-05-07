package org.evolboot.system.domain.dictvalue.repository.jpa.convert;

import org.evolboot.system.domain.dictvalue.DictValueLocale;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
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
