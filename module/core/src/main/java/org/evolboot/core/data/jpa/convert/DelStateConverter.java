package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.domain.DelState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
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
