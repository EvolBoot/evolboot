package org.evolboot.pay.domain.shared.jpa;

import org.evolboot.shared.pay.PayoutOrderState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PayoutOrderStateConverter implements AttributeConverter<PayoutOrderState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PayoutOrderState attribute) {
        return attribute.getValue();
    }

    @Override
    public PayoutOrderState convertToEntityAttribute(Integer dbData) {
        return PayoutOrderState.convertTo(dbData);
    }
}
