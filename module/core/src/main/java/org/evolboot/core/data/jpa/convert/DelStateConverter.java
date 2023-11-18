package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.entity.DelState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
//@Converter(autoApply = true)
@Deprecated
public class DelStateConverter implements AttributeConverter<DelState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DelState attribute) {
        return attribute.getValue();
    }

    @Override
    public DelState convertToEntityAttribute(Integer dbData) {
        return DelState.convertTo(dbData);
    }

}
