package org.evolboot.im.domain.userconversation.repository.jpa.convert;


import org.evolboot.im.domain.userconversation.entity.UserConversationForbidTalkCause;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Set;

@Converter(autoApply = true)
public class UserConversationForbidTalkCauseSetConverter implements AttributeConverter<Set<UserConversationForbidTalkCause>, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Set<UserConversationForbidTalkCause> attribute) {
        return UserConversationForbidTalkCause.convertToSymbol(attribute);
    }

    @Override
    public Set<UserConversationForbidTalkCause> convertToEntityAttribute(Integer dbData) {
        return UserConversationForbidTalkCause.convertEnum(dbData);
    }
}
