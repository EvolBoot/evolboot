package org.evolboot.content.domain.qa.repository.jpa.convert;

import org.evolboot.content.domain.qa.QaLocale;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * 
 */
public class QaLocaleListConverter implements AttributeConverter<List<QaLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<QaLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<QaLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, QaLocale.class);
    }
}
