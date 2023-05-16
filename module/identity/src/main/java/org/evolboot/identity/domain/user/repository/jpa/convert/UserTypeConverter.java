package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.UserType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserTypeConverter implements AttributeConverter<UserType, Integer> {


    @Override
    public Integer convertToDatabaseColumn(UserType attribute) {
        return attribute.getValue();
    }

    @Override
    public UserType convertToEntityAttribute(Integer dbData) {
        return UserType.convertTo(dbData);
    }
}
