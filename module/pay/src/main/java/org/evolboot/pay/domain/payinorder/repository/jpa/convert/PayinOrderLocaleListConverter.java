package org.evolboot.pay.domain.payinorder.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class PayinOrderLocaleListConverter implements AttributeConverter<List<PayinOrderLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<PayinOrderLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<PayinOrderLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, PayinOrderLocale.class);
    }
}
