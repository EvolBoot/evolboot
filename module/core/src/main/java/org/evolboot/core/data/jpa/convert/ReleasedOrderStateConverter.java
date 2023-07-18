package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReleasedOrderState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
