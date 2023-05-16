package org.evolboot.pay.domain.releasedorder.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class ReleasedOrderLocaleListConverter implements AttributeConverter<List<ReleasedOrderLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<ReleasedOrderLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<ReleasedOrderLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, ReleasedOrderLocale.class);
    }
}
