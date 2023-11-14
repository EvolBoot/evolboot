package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReceiptOrderState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ReceiptOrderStateConverter implements AttributeConverter<ReceiptOrderState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReceiptOrderState attribute) {
        return attribute.getValue();
    }

    @Override
    public ReceiptOrderState convertToEntityAttribute(Integer dbData) {
        return ReceiptOrderState.convertTo(dbData);
    }

}
