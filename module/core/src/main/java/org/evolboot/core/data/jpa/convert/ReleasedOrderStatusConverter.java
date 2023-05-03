package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReleasedOrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ReleasedOrderStatusConverter implements AttributeConverter<ReleasedOrderStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReleasedOrderStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public ReleasedOrderStatus convertToEntityAttribute(Integer dbData) {
        return ReleasedOrderStatus.convertTo(dbData);
    }

}
