package org.evolboot.pay.domain.shared.jpa;


import org.evolboot.shared.pay.PayinOrderState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * 枚举 PayinOrderState 与 DB 整型值的转换器
 */
@Converter(autoApply = true)
public class PayinOrderStateConverter implements AttributeConverter<PayinOrderState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PayinOrderState attribute) {
        return attribute.getValue();
    }

    @Override
    public PayinOrderState convertToEntityAttribute(Integer dbData) {
        return PayinOrderState.convertTo(dbData);
    }

}
