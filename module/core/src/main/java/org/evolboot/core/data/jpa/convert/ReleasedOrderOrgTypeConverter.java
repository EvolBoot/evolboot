package org.evolboot.core.data.jpa.convert;


import org.evolboot.shared.pay.ReleasedOrderOrgType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class ReleasedOrderOrgTypeConverter implements AttributeConverter<ReleasedOrderOrgType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ReleasedOrderOrgType attribute) {
        return attribute.getValue();
    }

    @Override
    public ReleasedOrderOrgType convertToEntityAttribute(Integer dbData) {
        return ReleasedOrderOrgType.convertTo(dbData);
    }

}
