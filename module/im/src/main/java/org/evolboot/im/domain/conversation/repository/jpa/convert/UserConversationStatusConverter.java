package org.evolboot.im.domain.conversation.repository.jpa.convert;


import org.evolboot.im.domain.userconversation.UserConversationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class UserConversationStatusConverter implements AttributeConverter<UserConversationStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserConversationStatus UserConversationStatus) {
        return UserConversationStatus.getValue();
    }

    @Override
    public UserConversationStatus convertToEntityAttribute(Integer value) {
        return UserConversationStatus.convertTo(value);
    }

}
