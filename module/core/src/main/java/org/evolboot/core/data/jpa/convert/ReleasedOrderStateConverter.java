package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReleasedOrderState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ReleasedOrderStateConverter implements AttributeConverter<ReleasedOrderState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReleasedOrderState attribute) {
        return attribute.getValue();
    }

    @Override
    public ReleasedOrderState convertToEntityAttribute(Integer dbData) {
        return ReleasedOrderState.convertTo(dbData);
    }

}
