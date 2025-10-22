package org.evolboot.pay.domain.payoutorder.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class PayoutOrderLocaleListConverter implements AttributeConverter<List<PayoutOrderLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<PayoutOrderLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<PayoutOrderLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, PayoutOrderLocale.class);
    }
}
