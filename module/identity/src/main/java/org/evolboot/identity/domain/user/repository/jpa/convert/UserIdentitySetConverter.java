package org.evolboot.identity.domain.user.repository.jpa.convert;

import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.lang.UserIdentity;

import jakarta.persistence.AttributeConverter;
import org.hibernate.annotations.JavaTypeRegistration;
import org.hibernate.annotations.JdbcTypeRegistration;
import org.hibernate.type.descriptor.java.IntegerJavaType;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.hibernate.type.descriptor.jdbc.JdbcType;

import java.util.Set;

//@Converter(autoApply = true)

/**
 * 这个就不用了，没办法做匹配
 */
@Deprecated
@Slf4j
public class UserIdentitySetConverter implements AttributeConverter<Set<UserIdentity>, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Set<UserIdentity> attribute) {
        log.info("UserIdentitySetConverter 转换");
        return UserIdentity.convertToSymbol(attribute);
    }

    @Override
    public Set<UserIdentity> convertToEntityAttribute(Integer dbData) {
        log.info("UserIdentitySetConverter 转换2");
        return UserIdentity.convertEnum(dbData);
    }
}
