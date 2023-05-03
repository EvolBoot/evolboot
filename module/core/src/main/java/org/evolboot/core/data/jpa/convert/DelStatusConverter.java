package org.evolboot.core.data.jpa.convert;

import org.evolboot.core.domain.DelStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class DelStatusConverter implements AttributeConverter<DelStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DelStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public DelStatus convertToEntityAttribute(Integer dbData) {
        return DelStatus.convertTo(dbData);
    }

}
