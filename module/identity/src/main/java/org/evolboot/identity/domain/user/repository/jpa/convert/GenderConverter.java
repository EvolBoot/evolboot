package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.entity.Gender;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Deprecated
//@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {


    @Override
    public Integer convertToDatabaseColumn(Gender attribute) {
        return Objects.requireNonNullElse(attribute, Gender.UNKNOWN).getValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        return Gender.convertTo(dbData);
    }
}
