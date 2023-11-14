package org.evolboot.im.domain.chatrecord.repository.jpa.convert;


import org.evolboot.im.domain.chatrecord.message.MessageState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * @author evol
 */
@Converter(autoApply = true)
public class MessageStateConverter implements AttributeConverter<MessageState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MessageState MessageState) {
        return MessageState.getValue();
    }

    @Override
    public MessageState convertToEntityAttribute(Integer value) {
        return MessageState.convertTo(value);
    }

}
