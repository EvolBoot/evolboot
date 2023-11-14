package org.evolboot.im.domain.conversation.repository.jpa.convert;


import org.evolboot.im.domain.userconversation.entity.UserConversationState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class UserConversationStateConverter implements AttributeConverter<UserConversationState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserConversationState UserConversationState) {
        return UserConversationState.getValue();
    }

    @Override
    public UserConversationState convertToEntityAttribute(Integer value) {
        return UserConversationState.convertTo(value);
    }

}
