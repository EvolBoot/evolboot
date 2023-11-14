package org.evolboot.im.domain.conversation.repository.jpa.convert;

import com.google.common.collect.Lists;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.im.domain.conversation.entity.ConversationLocale;
import org.springframework.util.StringUtils;

import jakarta.persistence.AttributeConverter;
import java.util.List;

/**
 * @author evol
 * @date 2023-05-02 21:43:03
 */
public class ConversationLocaleListConverter implements AttributeConverter<List<ConversationLocale>, String> {

    @Override
    public String convertToDatabaseColumn(List<ConversationLocale> attribute) {
        return ExtendObjects.isNull(attribute) ? null : JsonUtil.stringify(attribute);
    }

    @Override
    public List<ConversationLocale> convertToEntityAttribute(String dbData) {
        return StringUtils.isEmpty(dbData) ? Lists.newArrayList() : JsonUtil.parse(dbData, List.class, ConversationLocale.class);
    }
}
