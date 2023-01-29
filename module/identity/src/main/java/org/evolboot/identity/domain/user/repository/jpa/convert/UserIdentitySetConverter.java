package org.evolboot.identity.domain.user.repository.jpa.convert;

import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.lang.UserIdentity;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserIdentitySetConverter implements AttributeConverter<Set<UserIdentity>, String> {

    @Override
    public String convertToDatabaseColumn(Set<UserIdentity> attribute) {
        return Objects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public Set<UserIdentity> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? new HashSet<>() : JsonUtil.parse(dbData, Set.class, UserIdentity.class);
    }
}
