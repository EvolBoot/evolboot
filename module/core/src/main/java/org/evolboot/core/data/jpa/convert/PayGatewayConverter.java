package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.PayGateway;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class PayGatewayConverter implements AttributeConverter<PayGateway,Integer> {
    @Override
    public Integer convertToDatabaseColumn(PayGateway attribute) {
        return attribute.getValue();
    }

    @Override
    public PayGateway convertToEntityAttribute(Integer dbData) {
        return PayGateway.convertTo(dbData);
    }

}
