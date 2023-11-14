package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.shared.lang.UserIdentity;

import jakarta.persistence.AttributeConverter;
import java.util.Set;

public class UserIdentitySetConverter implements AttributeConverter<Set<UserIdentity>, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Set<UserIdentity> attribute) {
        return UserIdentity.convertToSymbol(attribute);
    }

    @Override
    public Set<UserIdentity> convertToEntityAttribute(Integer dbData) {
        return UserIdentity.convertEnum(dbData);
    }
}
