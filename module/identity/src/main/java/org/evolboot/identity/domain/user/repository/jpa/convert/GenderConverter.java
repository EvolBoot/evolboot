package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.Gender;
import org.evolboot.identity.domain.user.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Gender attribute) {
        return attribute.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        return Gender.convertTo(dbData);
    }
}
