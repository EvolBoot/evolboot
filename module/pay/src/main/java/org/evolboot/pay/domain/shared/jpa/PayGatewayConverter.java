package org.evolboot.pay.domain.shared.jpa;


import org.evolboot.shared.pay.PayGateway;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class PayGatewayConverter implements AttributeConverter<PayGateway, Integer> {
    @Override
    public Integer convertToDatabaseColumn(PayGateway attribute) {
        return attribute.getValue();
    }

    @Override
    public PayGateway convertToEntityAttribute(Integer dbData) {
        return PayGateway.convertTo(dbData);
    }

}
