package org.evolboot.pay.domain.shared.jpa;

import org.evolboot.shared.pay.PayoutOrderOrgType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PayoutOrderOrgTypeConverter implements AttributeConverter<PayoutOrderOrgType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PayoutOrderOrgType attribute) {
        return attribute.getValue();
    }

    @Override
    public PayoutOrderOrgType convertToEntityAttribute(Integer dbData) {
        return PayoutOrderOrgType.convertTo(dbData);
    }
}
