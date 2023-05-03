package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReceiptOrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ReceiptOrderStatusConverter implements AttributeConverter<ReceiptOrderStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReceiptOrderStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public ReceiptOrderStatus convertToEntityAttribute(Integer dbData) {
        return ReceiptOrderStatus.convertTo(dbData);
    }

}
