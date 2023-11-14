package org.evolboot.identity.domain.permission.repository.jpa;


import org.evolboot.identity.domain.permission.entity.Type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
