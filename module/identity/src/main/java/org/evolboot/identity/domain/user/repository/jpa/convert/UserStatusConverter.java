package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.core.util.JsonUtil;
import org.evolboot.identity.domain.user.UserStatus;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
