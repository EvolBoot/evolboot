package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.entity.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {


    @Override
    public Integer convertToDatabaseColumn(UserStatus attribute) {
        return attribute.getValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
        return UserStatus.convertTo(dbData);
    }
}
