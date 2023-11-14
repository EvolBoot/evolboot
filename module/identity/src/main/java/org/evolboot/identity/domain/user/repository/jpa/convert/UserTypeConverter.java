package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.entity.UserType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {


    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return Objects.requireNonNullElse(attribute, UserType.NORMAL).getValue();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return UserType.convertTo(dbData);
    }
}
