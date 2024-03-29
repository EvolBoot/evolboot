package org.evolboot.pay.domain.paygatewayaccount.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccountLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class PayGatewayAccountLocaleListConverter implements AttributeConverter<List<PayGatewayAccountLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<PayGatewayAccountLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<PayGatewayAccountLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, PayGatewayAccountLocale.class);
    }
}
