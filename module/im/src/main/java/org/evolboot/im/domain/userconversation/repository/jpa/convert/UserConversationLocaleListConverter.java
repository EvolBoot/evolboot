package org.evolboot.im.domain.userconversation.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.userconversation.UserConversationLocale;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-02 23:36:54
 */
public class UserConversationLocaleListConverter implements AttributeConverter<List<UserConversationLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<UserConversationLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<UserConversationLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, UserConversationLocale.class);
    }
}
