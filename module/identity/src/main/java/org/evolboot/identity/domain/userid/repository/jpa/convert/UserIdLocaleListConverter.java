package org.evolboot.identity.domain.userid.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.identity.domain.userid.entity.UserIdLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 */
public class UserIdLocaleListConverter implements AttributeConverter<List<UserIdLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserIdLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<UserIdLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, UserIdLocale.class);
    }
}
