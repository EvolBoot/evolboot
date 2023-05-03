package org.evolboot.identity.domain.permission.repository.jpa;


import org.evolboot.identity.domain.permission.Type;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class TypeConverter implements AttributeConverter<Type, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Type attribute) {
        return attribute.getValue();
    }

    @Override
    public Type convertToEntityAttribute(Integer dbData) {
        return Type.convertTo(dbData);
    }

}
