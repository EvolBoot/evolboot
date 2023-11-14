package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.identity.domain.user.entity.UserState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class UserStateConverter implements AttributeConverter<UserState, Integer> {


    @Override
    public Integer convertToDatabaseColumn(UserState attribute) {
        return Objects.requireNonNullElse(attribute, UserState.ACTIVE).getValue();
    }

    @Override
    public UserState convertToEntityAttribute(Integer dbData) {
        return UserState.convertTo(dbData);
    }
}
